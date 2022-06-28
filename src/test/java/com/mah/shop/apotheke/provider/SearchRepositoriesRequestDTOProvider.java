package com.mah.shop.apotheke.provider;

import com.mah.shop.apotheke.rest.dto.SearchRepositoriesRequestDTO;

import java.time.LocalDate;

public class SearchRepositoriesRequestDTOProvider {

    public static SearchRepositoriesRequestDTO buildSearchRepositoriesRequestDTO() {

        return SearchRepositoriesRequestDTO.builder()
                .creationDate(LocalDate.now().toString())
                .build();
    }
}
