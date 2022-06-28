package com.mah.shop.apotheke.rest.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchRepositoriesResponseDTO {

    private long id;

    private String name;

    private String fullName;

    private String url;

    private String description;

    private LocalDate createdAt;

    private String language;

    private long stars;
}
