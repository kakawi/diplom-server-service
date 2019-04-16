package com.hlebon.adsweb.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CampaignListDto {
    private List<CampaignDto> campaigns;
    private PageableDto pageable;
}
