package com.hlebon.adsweb.service.searchable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequestObject {
    private final int size;
    private final int page;
    private String sort;
    private String order;

    public PageRequestObject(int size, int page) {
        this.size = size;
        this.page = page;
    }
}
