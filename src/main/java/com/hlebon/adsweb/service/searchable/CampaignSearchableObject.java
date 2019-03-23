package com.hlebon.adsweb.service.searchable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor
@Getter
public class CampaignSearchableObject {
    private final boolean isFetchStatistics;
    private final boolean isFetchCurrentBudget;
}
