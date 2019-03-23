package com.hlebon.adsweb.web;

import com.hlebon.adsweb.service.CampaignService;
import com.hlebon.adsweb.web.dto.CampaignDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(final CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping
    public List<CampaignDto> findAll() {
        return campaignService.findAll();
    }

    @GetMapping("/{campaignId}")
    public CampaignDto findById(@PathVariable("campaignId") final Long campaignId) {
        return campaignService.findById(campaignId);
    }

}
