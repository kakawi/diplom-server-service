package com.hlebon.adsweb.web.mapper;

import com.hlebon.adsweb.repository.entity.CampaignEntity;
import com.hlebon.adsweb.web.dto.CampaignDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CampaignMapper {

    CampaignDto mapToDto(CampaignEntity entity);

    CampaignEntity mapToEntity(CampaignDto dto);
}
