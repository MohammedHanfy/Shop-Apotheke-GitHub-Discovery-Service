package com.mah.shop.apotheke.service.mappers;

import com.mah.shop.apotheke.rest.dto.SearchRepositoriesRequestDTO;
import com.mah.shop.apotheke.service.model.SearchRepositoriesModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SearchRepositoriesModelMapper {

    SearchRepositoriesModelMapper INSTANCE = Mappers.getMapper(SearchRepositoriesModelMapper.class);

    @Mapping(target = "creationDate", source = "creationDate", dateFormat = "yyyy-MM-dd")
    SearchRepositoriesModel mapToModel(SearchRepositoriesRequestDTO searchRepositoriesRequestDTO);
}
