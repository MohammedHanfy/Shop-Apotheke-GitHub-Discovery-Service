package com.mah.shop.apotheke.service;

import com.mah.shop.apotheke.exceptions.ExternalRequestFailedException;
import com.mah.shop.apotheke.service.model.GithubRepositoriesResponse;
import com.mah.shop.apotheke.service.model.RepositoryModel;
import com.mah.shop.apotheke.service.model.SearchRepositoriesModel;
import com.mah.shop.apotheke.service.provider.GithubRepositoriesResponseProvider;
import com.mah.shop.apotheke.service.provider.SearchRepositoriesModelProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RepositoriesDiscoveryServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RepositoriesDiscoveryService repositoriesDiscoveryService;

    private static final GithubRepositoriesResponse GITHUB_REPOSITORIES_RESPONSE = GithubRepositoriesResponseProvider.buildGithubRepositoriesResponse();

    private static final SearchRepositoriesModel SEARCH_REPOSITORIES_MODEL = SearchRepositoriesModelProvider.buildSearchRepositoriesModel();

    private static final String REQUEST_TO_EXTERNAL_SERVICE_HAS_FAILED = "Request to external service has failed with HttpStatus code %s, and errorMessage: %s";

    @Test
    public void searchRepositories_returnsOK() {

        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.any(URI.class),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GithubRepositoriesResponse>>any()))
                .thenReturn(new ResponseEntity<>(GITHUB_REPOSITORIES_RESPONSE, HttpStatus.OK));

        List<RepositoryModel> repositoryModelList = repositoriesDiscoveryService.searchRepositories(SEARCH_REPOSITORIES_MODEL);

        Assertions.assertEquals(repositoryModelList.size(), 1);
    }

    @Test
    public void searchRepositories_returnsOKWithoutBody_throwsExternalRequestFailedException() {

        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.any(URI.class),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GithubRepositoriesResponse>>any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        Throwable externalRequestFailedException = Assertions.assertThrows(
                ExternalRequestFailedException.class, () -> repositoriesDiscoveryService.searchRepositories(SEARCH_REPOSITORIES_MODEL));

        Assertions.assertEquals(externalRequestFailedException.getMessage(),
                String.format(REQUEST_TO_EXTERNAL_SERVICE_HAS_FAILED, HttpStatus.OK, "empty response body."));
    }

    @Test
    public void searchRepositories_returnsUnprocessableEntity_throwsExternalRequestFailedException() {

        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.any(URI.class),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GithubRepositoriesResponse>>any()))
                .thenReturn(new ResponseEntity<>(new GithubRepositoriesResponse(), HttpStatus.UNPROCESSABLE_ENTITY));

        Throwable externalRequestFailedException = Assertions.assertThrows(
                ExternalRequestFailedException.class, () -> repositoriesDiscoveryService.searchRepositories(SEARCH_REPOSITORIES_MODEL));

        Assertions.assertEquals(externalRequestFailedException.getMessage(),
                String.format(REQUEST_TO_EXTERNAL_SERVICE_HAS_FAILED, HttpStatus.UNPROCESSABLE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase()));
    }

    @Test
    public void searchRepositories_returnsServiceUnavailable_throwsExternalRequestFailedException() {

        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.any(URI.class),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<GithubRepositoriesResponse>>any()))
                .thenReturn(new ResponseEntity<>(new GithubRepositoriesResponse(), HttpStatus.SERVICE_UNAVAILABLE));

        Throwable externalRequestFailedException = Assertions.assertThrows(
                ExternalRequestFailedException.class, () -> repositoriesDiscoveryService.searchRepositories(SEARCH_REPOSITORIES_MODEL));

        Assertions.assertEquals(externalRequestFailedException.getMessage(),
                String.format(REQUEST_TO_EXTERNAL_SERVICE_HAS_FAILED, HttpStatus.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()));
    }
}