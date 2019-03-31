package com.hlebon.adsweb.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private Set<ProductStatisticDto> productStatistics;

    @Setter
    @Getter
    public static class ProductStatisticDto {
        private int weekNumber;
        private Long sell;
    }
}
