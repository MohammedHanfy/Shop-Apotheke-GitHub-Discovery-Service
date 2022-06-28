package com.mah.shop.apotheke.rest.mappers;

import com.mah.shop.apotheke.rest.dto.SearchRepositoriesRequestDTO;
import com.mah.shop.apotheke.service.model.SearchRepositoriesModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SearchRepositoriesRequestDTOMapper {

    SearchRepositoriesRequestDTOMapper INSTANCE = Mappers.getMapper(SearchRepositoriesRequestDTOMapper.class);

    SearchRepositoriesRequestDTO mapToDTO(SearchRepositoriesModel searchRepositoriesModel);
}
