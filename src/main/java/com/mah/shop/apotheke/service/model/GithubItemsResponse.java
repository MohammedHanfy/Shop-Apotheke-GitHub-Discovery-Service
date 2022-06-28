package com.mah.shop.apotheke.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubItemsResponse {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "full_name")
    private String fullName;

    @JsonProperty(value = "html_url")
    private String url;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "created_at")
    private LocalDate createdAt;

    @JsonProperty(value = "language")
    private String language;

    @JsonProperty(value = "stargazers_count")
    private long stars;
}
