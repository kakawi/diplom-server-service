package com.hlebon.adsweb.web;

import com.hlebon.adsweb.service.CampaignService;
import com.hlebon.adsweb.service.searchable.CampaignSearchableObject;
import com.hlebon.adsweb.service.searchable.PageRequestObject;
import com.hlebon.adsweb.web.dto.CampaignDto;
import com.hlebon.adsweb.web.dto.CampaignListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService campaignService;

    @GetMapping()
    public CampaignListDto findAll(
            @RequestParam(value = "isFetchStatistic", defaultValue = "false") boolean isFetchStatistic,
            @RequestParam(value = "isFetchCurrentValue", defaultValue = "false") boolean isFetchCurrentValue,
            @RequestParam(value = "isFetchProductStatistic", defaultValue = "false") boolean isFetchProductStatistic,
            @RequestParam(value = "size", defaultValue = "2") int size,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "order", defaultValue = "ASC") String order
    ) {
        final PageRequestObject pageRequest = new PageRequestObject(size, page);
        if (sort != null) {
            pageRequest.setSort(sort);
            pageRequest.setOrder(order);
        }
        final CampaignSearchableObject campaignSearchableObject = new CampaignSearchableObject(
                isFetchStatistic,
                isFetchCurrentValue,
                isFetchProductStatistic
        );
        campaignSearchableObject.setPageRequest(pageRequest);
        return campaignService.findPage(campaignSearchableObject);
    }

    @GetMapping("/{campaignId}")
    public CampaignDto findById(
            @PathVariable("campaignId") final Long campaignId,
            @RequestParam(value = "isFetchStatistic", defaultValue = "false") boolean isFetchStatistic,
            @RequestParam(value = "isFetchCurrentValue", defaultValue = "false") boolean isFetchCurrentValue,
            @RequestParam(value = "isFetchProductStatistic", defaultValue = "false") boolean isFetchProductStatistic
    ) {
        final CampaignSearchableObject campaignSearchableObject = new CampaignSearchableObject(isFetchStatistic, isFetchCurrentValue, isFetchProductStatistic);
        return campaignService.findById(campaignId, campaignSearchableObject);
    }

}
