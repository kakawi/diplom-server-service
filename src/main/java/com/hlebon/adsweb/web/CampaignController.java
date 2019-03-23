package com.hlebon.adsweb.web;

import com.hlebon.adsweb.repository.entity.CampaignEntity;
import com.hlebon.adsweb.service.CampaignService;
import com.hlebon.adsweb.web.dto.CampaignDto;
import com.hlebon.adsweb.web.mapper.CampaignMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignMapper campaignMapper;
    private final CampaignService campaignService;


    @GetMapping
    public List<CampaignDto> findAll() {
        final List<CampaignEntity> entities = campaignService.findAll();
        return entities.stream().map(campaignMapper::mapToDto).collect(Collectors.toList());
    }

    @GetMapping("/{campaignId}")
    public CampaignDto findById(@PathVariable("campaignId") final Long campaignId) {
        return campaignService.findById(campaignId);
    }

}
