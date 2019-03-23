package com.hlebon.adsweb.web.dto;

import java.util.List;

public class AdSetDto {
    private Long id;
    private String name;
    private List<Long> impressionsHistory;
    private List<Long> clicksHistory;
    private List<Long> costHistory;
    private Long currentValue;
}
