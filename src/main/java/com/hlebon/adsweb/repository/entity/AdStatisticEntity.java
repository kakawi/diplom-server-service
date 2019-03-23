package com.hlebon.adsweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ad_statistic")
@Getter
@Setter
public class AdStatisticEntity {
    @Id
    private Long id;
    private Integer weekNumber;
    private Long spends;
    private Long impressions;
    private Long clicks;
    private Integer year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdStatisticEntity that = (AdStatisticEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(weekNumber, that.weekNumber) &&
                Objects.equals(spends, that.spends) &&
                Objects.equals(impressions, that.impressions) &&
                Objects.equals(clicks, that.clicks) &&
                Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, weekNumber, spends, impressions, clicks, year);
    }
}
