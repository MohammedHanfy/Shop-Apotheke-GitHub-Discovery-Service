package com.mah.shop.apotheke.service.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryModel {

    private long id;

    private String name;

    private String fullName;

    private String url;

    private String description;

    private LocalDate createdAt;

    private String language;

    private long stars;
}
