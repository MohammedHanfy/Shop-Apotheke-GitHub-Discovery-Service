package com.mah.shop.apotheke.rest.dto;

import com.mah.shop.apotheke.validators.ValidSearchRepositoriesRequestDTO;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ValidSearchRepositoriesRequestDTO
public class SearchRepositoriesRequestDTO {

    private String creationDate;

    private String language;

    private Integer limit;

    private Integer offset;
}
