package com.hlebon.adsweb.web;

import com.hlebon.adsweb.service.CampaignService;
import com.hlebon.adsweb.service.searchable.CampaignSearchableObject;
import com.hlebon.adsweb.web.dto.CampaignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService campaignService;


    @GetMapping()
    public List<CampaignDto> findAll(
            @RequestParam(value = "isFetchStatistic", defaultValue = "false") boolean isFetchStatistic,
            @RequestParam(value = "isFetchCurrentValue", defaultValue = "false") boolean isFetchCurrentValue
    ) {
        final CampaignSearchableObject campaignSearchableObject = new CampaignSearchableObject(isFetchStatistic, isFetchCurrentValue);
        final List<CampaignDto> dtos = campaignService.findAll(campaignSearchableObject);
        return dtos;
    }

    @GetMapping("/{campaignId}")
    public CampaignDto findById(@PathVariable("campaignId") final Long campaignId) {
        return campaignService.findById(campaignId);
    }

}
