package com.mah.shop.apotheke.rest;

import com.mah.shop.apotheke.rest.dto.SearchRepositoriesRequestDTO;
import com.mah.shop.apotheke.rest.dto.SearchRepositoriesResponseDTO;
import com.mah.shop.apotheke.rest.mappers.SearchRepositoriesResponseDTOMapper;
import com.mah.shop.apotheke.service.RepositoriesDiscoveryService;
import com.mah.shop.apotheke.service.mappers.SearchRepositoriesModelMapper;
import com.mah.shop.apotheke.service.model.SearchRepositoriesModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/repositoriesDiscovery")
@RequiredArgsConstructor
public class RepositoriesDiscoveryResource {

    private final RepositoriesDiscoveryService repositoriesDiscoveryService;

    @PostMapping(value = "/search")
    public List<SearchRepositoriesResponseDTO> searchRepositories(
            @RequestBody @Valid final SearchRepositoriesRequestDTO searchRepositoriesRequestDTO) {

        final SearchRepositoriesModel searchRepositoriesModel = SearchRepositoriesModelMapper.INSTANCE.mapToModel(searchRepositoriesRequestDTO);

        return repositoriesDiscoveryService.searchRepositories(searchRepositoriesModel)
                .stream()
                .map(SearchRepositoriesResponseDTOMapper.INSTANCE::mapToDTO)
                .collect(Collectors.toList());
    }
}

