package com.hlebon.adsweb.web;

import com.hlebon.adsweb.service.AdSetService;
import com.hlebon.adsweb.service.searchable.AdSetMetadataObject;
import com.hlebon.adsweb.service.searchable.AdSetSearchableObject;
import com.hlebon.adsweb.web.dto.AdSetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/adsets")
@RequiredArgsConstructor
public class AdSetController {
    private final AdSetService adSetService;

    @PostMapping(path = "/all")
    public List<AdSetDto> findAll(
            @RequestBody final AdSetSearchableObject adSetSearchableObject,
            @RequestParam(value = "isFetchStatistic", defaultValue = "false") boolean isFetchStatistic,
            @RequestParam(value = "isFetchCurrentValue", defaultValue = "false") boolean isFetchCurrentValue
    ) {
        final AdSetMetadataObject adSetMetadataObject = new AdSetMetadataObject(isFetchStatistic, isFetchCurrentValue);
        final List<AdSetDto> dtos = adSetService.findAll(adSetSearchableObject, adSetMetadataObject);
        return dtos;
    }

    @PostMapping("/{adSetId}")
    public AdSetDto findById(
            @PathVariable("adSetId") final Long adSetId,
            @RequestParam(value = "isFetchStatistic", defaultValue = "false") boolean isFetchStatistic,
            @RequestParam(value = "isFetchCurrentValue", defaultValue = "false") boolean isFetchCurrentValue
    ) {
        final AdSetMetadataObject adSetMetadataObject = new AdSetMetadataObject(isFetchCurrentValue, isFetchCurrentValue);
        return adSetService.findById(adSetId, adSetMetadataObject);
    }

}
