package com.hlebon.adsweb.service.searchable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AdSetMetadataObject {
    private final boolean isFetchStatistics;
    private final boolean isFetchCurrentBudget;
}
