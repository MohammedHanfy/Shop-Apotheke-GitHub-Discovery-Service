package com.mah.shop.apotheke.service;

import com.mah.shop.apotheke.exceptions.ExternalRequestFailedException;
import com.mah.shop.apotheke.service.mappers.RepositoryModelMapper;
import com.mah.shop.apotheke.service.model.GithubRepositoriesResponse;
import com.mah.shop.apotheke.service.model.RepositoryModel;
import com.mah.shop.apotheke.service.model.SearchRepositoriesModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepositoriesDiscoveryService {

    private static final String Q_QUERY_PARAM = "q";

    private static final String ACCEPT_HEADER = "accept";

    private static final String ACCEPT_HEADER_VALUE = "application/vnd.github.v3+json";

    private static final String Q_QUERY_PARAM_CREATED_GREATER_THAN = "created:>";

    private static final String Q_QUERY_PARAM_LANGUAGE = "language:";

    private static final String PLUS_SIGN = "+";

    private static final String SORT_QUERY_PARAM = "sort";

    private static final String SORT_QUERY_PARAM_VALUE = "stars";

    private static final String ORDER_QUERY_PARAM = "order";

    private static final String ORDER_QUERY_PARAM_VALUE = "desc";

    private static final String PER_PAGE_QUERY_PARAM = "per_page";

    private static final String PAGE_QUERY_PARAM = "page";

    private static final String GITHUB_SEARCH_REPOSITORIES_URL = "https://api.github.com/search/repositories";

    private final RestTemplate restTemplate;

    public List<RepositoryModel> searchRepositories(final SearchRepositoriesModel searchRepositoriesModel) {

        final HttpHeaders headers = new HttpHeaders();
        headers.set(ACCEPT_HEADER, ACCEPT_HEADER_VALUE);

        final ResponseEntity<GithubRepositoriesResponse> responseEntity = restTemplate.exchange(
                buildGithubSearchRepositoriesURI(searchRepositoriesModel),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                GithubRepositoriesResponse.class);

        return handleSearchRepositoriesResponse(responseEntity);
    }

    private URI buildGithubSearchRepositoriesURI(final SearchRepositoriesModel searchRepositoriesModel) {

        StringBuilder queryStringBuilder = new StringBuilder();

        boolean hasPlusSign = false;

        if (!Objects.isNull(searchRepositoriesModel.getCreationDate())) {

            queryStringBuilder.append(Q_QUERY_PARAM_CREATED_GREATER_THAN)
                    .append(searchRepositoriesModel.getCreationDate());

            hasPlusSign = true;
        }

        if (StringUtils.isNotBlank(searchRepositoriesModel.getLanguage())) {

            if (hasPlusSign) {
                queryStringBuilder.append(PLUS_SIGN);
            }

            queryStringBuilder.append(Q_QUERY_PARAM_LANGUAGE)
                    .append(searchRepositoriesModel.getLanguage());
        }

        return UriComponentsBuilder
                .fromUriString(GITHUB_SEARCH_REPOSITORIES_URL)
                .queryParam(Q_QUERY_PARAM, queryStringBuilder.toString())
                .queryParam(SORT_QUERY_PARAM, SORT_QUERY_PARAM_VALUE)
                .queryParam(ORDER_QUERY_PARAM, ORDER_QUERY_PARAM_VALUE)
                .queryParam(PER_PAGE_QUERY_PARAM, searchRepositoriesModel.getLimit())
                .queryParam(PAGE_QUERY_PARAM, searchRepositoriesModel.getOffset())
                .build().toUri();
    }

    private List<RepositoryModel> handleSearchRepositoriesResponse(final ResponseEntity<GithubRepositoriesResponse> responseEntity) {

        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {

            final Optional<GithubRepositoriesResponse> githubRepositoriesResponseOptional = Optional.ofNullable(responseEntity.getBody());

            if (githubRepositoriesResponseOptional.isPresent()) {

                return githubRepositoriesResponseOptional.get().getItems()
                        .stream()
                        .map(RepositoryModelMapper.INSTANCE::mapToModel)
                        .collect(Collectors.toList());
            } else {

                throw new ExternalRequestFailedException(HttpStatus.OK, "empty response body.");
            }
        } else {

            throw new ExternalRequestFailedException(responseEntity.getStatusCode(), responseEntity.getStatusCode().getReasonPhrase());
        }
    }
}
