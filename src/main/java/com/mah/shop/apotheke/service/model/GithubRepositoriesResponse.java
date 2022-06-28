package com.mah.shop.apotheke.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepositoriesResponse {

    @JsonProperty(value = "total_count")
    private int totalCount;

    @JsonProperty(value = "incomplete_results")
    private boolean incompleteResults;

    @JsonProperty(value = "items")
    private List<GithubItemsResponse> items;

    @JsonProperty(value = "message")
    private String message;
}
