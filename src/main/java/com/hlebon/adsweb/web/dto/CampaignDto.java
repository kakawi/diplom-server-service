package com.hlebon.adsweb.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CampaignDto {
    private Long id;
    private String name;
    private List<Long> impressionsHistory;
    private List<Long> clicksHistory;
    private List<Long> costHistory;
    private Long currentValue;
}
