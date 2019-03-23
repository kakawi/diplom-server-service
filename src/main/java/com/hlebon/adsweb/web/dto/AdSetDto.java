package com.hlebon.adsweb.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class AdSetDto {
    private Long id;
    private String name;
    private Set<AdSetStatisticDto> adSetStatistics;

    public Long getId() {
        return id;
    }

    public AdSetDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdSetDto setName(String name) {
        this.name = name;
        return this;
    }

    public Set<AdSetStatisticDto> getAdSetStatistics() {
        return adSetStatistics;
    }

    public AdSetDto setAdSetStatistics(Set<AdSetStatisticDto> adSetStatistics) {
        this.adSetStatistics = adSetStatistics;
        return this;
    }

    @Setter
    @Getter
    public static class AdSetStatisticDto {
        private int weekNumber;
        private Long impressions;
        private Long spends;
        private Long clicks;
    }
}
