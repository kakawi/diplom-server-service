package com.hlebon.adsweb.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CampaignStatistic {

    private final Long id;
    private final String name;
    private Statistic statistic;

    @Getter
    @Setter
    public static class Statistic {

        private final PeriodType type;
        private final List<Statistic.CampaignStatisticDto> campaignStatistics;

        public Statistic(PeriodType type, List<Statistic.CampaignStatisticDto> campaignStatistics) {
            this.type = type;
            this.campaignStatistics = campaignStatistics;
        }

        @Getter
        @Setter
        @RequiredArgsConstructor
        public static class CampaignStatisticDto {
            private final Long impressions;
            private final Long clicks;
            private final Long spends;
            private final Statistic.CampaignStatisticDto.Period period;

            public static class WeekPeriod implements Statistic.CampaignStatisticDto.Period {
                private final int weekNumber;
                private final int year;

                public WeekPeriod(int weekNumber, int year) {
                    this.weekNumber = weekNumber;
                    this.year = year;
                }

                @Override
                public String getPeriod() {
                    return year + "|" + weekNumber;
                }
            }

            public static class DayPeriod implements Statistic.CampaignStatisticDto.Period {
                private final LocalDate day;

                public DayPeriod(LocalDate day) {
                    this.day = day;
                }

                @Override
                public String getPeriod() {
                    return String.valueOf(day.atStartOfDay().toEpochSecond(ZoneOffset.UTC));
                }
            }

            public static class MontnPeriod implements Statistic.CampaignStatisticDto.Period {
                private final int month;
                private final int year;

                public MontnPeriod(int month, int year) {
                    this.month = month;
                    this.year = year;
                }

                @Override
                public String getPeriod() {
                    return year + "|" + month;
                }
            }

            interface Period {
                String getPeriod();
            }
        }
    }
}
