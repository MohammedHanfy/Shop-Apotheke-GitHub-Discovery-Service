package com.mah.shop.apotheke.service.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchRepositoriesModel {

    private LocalDate creationDate;

    private String language;

    private Integer limit;

    private Integer offset;
}
