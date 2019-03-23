package com.hlebon.adsweb.service.mapper;

import com.hlebon.adsweb.repository.entity.AdSetEntity;
import com.hlebon.adsweb.web.dto.AdSetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdSetMapper {
    @Mapping(source = "id", target = "id")
    AdSetDto mapToDto(AdSetEntity entity);
    AdSetEntity mapToEntity(AdSetDto dto);
}
