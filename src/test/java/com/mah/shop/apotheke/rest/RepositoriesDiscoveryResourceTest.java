package com.mah.shop.apotheke.rest;

import com.mah.shop.apotheke.provider.SearchRepositoriesRequestDTOProvider;
import com.mah.shop.apotheke.rest.dto.SearchRepositoriesRequestDTO;
import com.mah.shop.apotheke.rest.dto.SearchRepositoriesResponseDTO;
import com.mah.shop.apotheke.service.RepositoriesDiscoveryService;
import com.mah.shop.apotheke.service.model.RepositoryModel;
import com.mah.shop.apotheke.service.provider.RepositoryModelProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RepositoriesDiscoveryResourceTest {

    @Mock
    private RepositoriesDiscoveryService repositoriesDiscoveryService;

    @InjectMocks
    private RepositoriesDiscoveryResource repositoriesDiscoveryResource;

    private static final SearchRepositoriesRequestDTO searchRepositoriesRequestDTO = SearchRepositoriesRequestDTOProvider.buildSearchRepositoriesRequestDTO();

    private static final RepositoryModel REPOSITORY_MODEL = RepositoryModelProvider.buildRepositoryModel();

    @Test
    public void searchRepositories() {

        Mockito.when(repositoriesDiscoveryService.searchRepositories(Mockito.any())).thenReturn(Collections.singletonList(REPOSITORY_MODEL));

        final List<SearchRepositoriesResponseDTO> searchRepositoriesResponseDTOList = repositoriesDiscoveryResource.searchRepositories(searchRepositoriesRequestDTO);

        Assertions.assertEquals(searchRepositoriesResponseDTOList.size(), 1);
    }
}