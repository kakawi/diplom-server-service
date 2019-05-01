package com.hlebon.adsweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "campaign_statistic_day")
@Getter
@Setter
public class CampaignStatisticDayEntity {
    @Id
    private Long id;
    @Column(name = "date_statistic")
    private LocalDate dateStatistic;
    private Long spends;
    private Long impressions;
    private Long clicks;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private CampaignEntity campaign;

    public int getYear() {
        return dateStatistic.getYear();
    }

    public int getMonth() {
        return dateStatistic.getMonthValue();
    }

    public int getWeekNumber() {
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return dateStatistic.get(woy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignStatisticDayEntity that = (CampaignStatisticDayEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dateStatistic, that.dateStatistic) &&
                Objects.equals(spends, that.spends) &&
                Objects.equals(impressions, that.impressions) &&
                Objects.equals(clicks, that.clicks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateStatistic, spends, impressions, clicks);
    }
}
