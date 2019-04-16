package com.hlebon.adsweb.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PageableDto {
    private final int totalPageNumber;
    private final int page;
    private final int size;
}
