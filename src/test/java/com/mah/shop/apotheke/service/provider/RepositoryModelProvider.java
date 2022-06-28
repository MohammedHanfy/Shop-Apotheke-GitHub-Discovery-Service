package com.mah.shop.apotheke.service.provider;

import com.mah.shop.apotheke.service.model.RepositoryModel;

import java.time.LocalDate;

public class RepositoryModelProvider {

    public static RepositoryModel buildRepositoryModel() {

        return RepositoryModel.builder()
                .id(1)
                .name("name")
                .fullName("full name")
                .url("url")
                .description("description")
                .createdAt(LocalDate.now())
                .language("java")
                .stars(10)
                .build();
    }
}
