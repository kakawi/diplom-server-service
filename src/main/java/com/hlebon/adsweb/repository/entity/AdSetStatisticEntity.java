package com.hlebon.adsweb.repository.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ad_set_statistic", schema = "diplom", catalog = "gleb")
public class AdSetStatisticEntity {
    private Long id;
    private Integer weekNumber;
    private Long costPerClick;
    private Long spend;
    private Long impressions;
    private Long clicks;
    private Integer year;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public _Dummy_ setId(Long id) {
        this.id = id;
        return this;
    }

    @Basic
    @Column(name = "week_number")
    public Integer getWeekNumber() {
        return weekNumber;
    }

    public _Dummy_ setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
        return this;
    }

    @Basic
    @Column(name = "cost_per_click")
    public Long getCostPerClick() {
        return costPerClick;
    }

    public _Dummy_ setCostPerClick(Long costPerClick) {
        this.costPerClick = costPerClick;
        return this;
    }

    @Basic
    @Column(name = "spend")
    public Long getSpend() {
        return spend;
    }

    public _Dummy_ setSpend(Long spend) {
        this.spend = spend;
        return this;
    }

    @Basic
    @Column(name = "impressions")
    public Long getImpressions() {
        return impressions;
    }

    public _Dummy_ setImpressions(Long impressions) {
        this.impressions = impressions;
        return this;
    }

    @Basic
    @Column(name = "clicks")
    public Long getClicks() {
        return clicks;
    }

    public _Dummy_ setClicks(Long clicks) {
        this.clicks = clicks;
        return this;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public _Dummy_ setYear(Integer year) {
        this.year = year;
        return this;
    }

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
