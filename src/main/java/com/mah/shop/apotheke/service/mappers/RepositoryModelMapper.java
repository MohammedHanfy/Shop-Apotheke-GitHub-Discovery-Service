package com.mah.shop.apotheke.service.mappers;

import com.mah.shop.apotheke.service.model.GithubItemsResponse;
import com.mah.shop.apotheke.service.model.RepositoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RepositoryModelMapper {

    RepositoryModelMapper INSTANCE = Mappers.getMapper(RepositoryModelMapper.class);

    RepositoryModel mapToModel(GithubItemsResponse githubItemsResponse);
}
