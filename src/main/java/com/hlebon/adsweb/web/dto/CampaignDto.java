package com.hlebon.adsweb.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CampaignDto {
    private Long id;
    private String name;
    private Set<CampaignStatisticDto> campaignStatistics;

    @Setter
    @Getter
    public static class CampaignStatisticDto {
        private int weekNumber;
        private Long impressions;
        private Long spends;
        private Long clicks;
        private int year;
        private Long sells; // from product statistic
    }
}
