package com.hlebon.adsweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ad_set_statistic")
@Getter
@Setter
public class AdSetStatisticEntity {
    @Id
    private Long id;
    private Integer weekNumber;
    private Long costPerClick;
    private Long spend;
    private Long impressions;
    private Long clicks;
    private Integer year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdSetStatisticEntity that = (AdSetStatisticEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(weekNumber, that.weekNumber) &&
                Objects.equals(costPerClick, that.costPerClick) &&
                Objects.equals(spend, that.spend) &&
                Objects.equals(impressions, that.impressions) &&
                Objects.equals(clicks, that.clicks) &&
                Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weekNumber, costPerClick, spend, impressions, clicks, year);
    }
}
