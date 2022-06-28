package com.mah.shop.apotheke.rest.mappers;

import com.mah.shop.apotheke.rest.dto.SearchRepositoriesResponseDTO;
import com.mah.shop.apotheke.service.model.RepositoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SearchRepositoriesResponseDTOMapper {

    SearchRepositoriesResponseDTOMapper INSTANCE = Mappers.getMapper(SearchRepositoriesResponseDTOMapper.class);

    SearchRepositoriesResponseDTO mapToDTO(RepositoryModel repositoryModel);
}
