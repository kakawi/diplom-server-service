package com.hlebon.adsweb.service.mapper;

import com.hlebon.adsweb.repository.entity.AdSetStatisticEntity;
import com.hlebon.adsweb.web.dto.AdSetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdSetStatisticMapper {
    @Mapping(source = "spends", target = "spends")
    AdSetDto.AdSetStatisticDto mapToDto(AdSetStatisticEntity entity);
    AdSetStatisticEntity mapToEntity(AdSetDto.AdSetStatisticDto dto);
}
