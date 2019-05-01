package com.hlebon.adsweb.service.searchable;

import com.hlebon.adsweb.service.PeriodType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@RequiredArgsConstructor
@Getter
public class CampaignSearchableWithTypeObject {
    private final boolean isFetchStatistics;
    private final boolean isFetchCurrentBudget;
    private final boolean isFetchProductStatistic;
    private final PeriodType type;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private PageRequestObject pageRequest;

}
