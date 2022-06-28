package com.mah.shop.apotheke.service.provider;

import com.mah.shop.apotheke.service.model.SearchRepositoriesModel;

import java.time.LocalDate;

public class SearchRepositoriesModelProvider {

    public static SearchRepositoriesModel buildSearchRepositoriesModel() {

        return SearchRepositoriesModel.builder()
                .creationDate(LocalDate.now())
                .language("Java")
                .build();
    }
}
