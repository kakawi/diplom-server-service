package com.hlebon.adsweb.service.mapper;

import com.hlebon.adsweb.repository.entity.CampaignStatisticEntity;
import com.hlebon.adsweb.web.dto.CampaignDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CampaignStatisticMapper {
    @Mapping(source = "spends", target = "spends")
    CampaignDto.CampaignStatisticDto mapToDto(CampaignStatisticEntity entity);
    CampaignStatisticEntity mapToEntity(CampaignDto.CampaignStatisticDto dto);
}
