package com.hlebon.adsweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "campaign_statistic")
@Getter
@Setter
public class CampaignStatisticEntity {
    @Id
    private Long id;
    @Column(name = "week_number")
    private Integer weekNumber;
    private Long spends;
    private Long impressions;
    private Long clicks;
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private CampaignEntity campaign;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignStatisticEntity that = (CampaignStatisticEntity) o;
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
