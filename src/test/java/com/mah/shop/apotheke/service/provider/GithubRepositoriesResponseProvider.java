package com.mah.shop.apotheke.service.provider;

import com.mah.shop.apotheke.service.model.GithubItemsResponse;
import com.mah.shop.apotheke.service.model.GithubRepositoriesResponse;

import java.time.LocalDate;
import java.util.Collections;

public class GithubRepositoriesResponseProvider {

    public static GithubRepositoriesResponse buildGithubRepositoriesResponse() {

        return GithubRepositoriesResponse.builder()
                .totalCount(1)
                .incompleteResults(false)
                .items(
                        Collections.singletonList(
                                GithubItemsResponse.builder()
                                        .id(1)
                                        .name("name")
                                        .fullName("full name")
                                        .url("url")
                                        .description("description")
                                        .createdAt(LocalDate.now())
                                        .language("java")
                                        .stars(10)
                                        .build()))
                .build();
    }
}
