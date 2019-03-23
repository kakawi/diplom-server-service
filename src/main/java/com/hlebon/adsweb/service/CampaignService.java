package com.hlebon.adsweb.service;

import com.hlebon.adsweb.web.dto.CampaignDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class CampaignService {

    private List<CampaignDto> campaignDtos = asList(
            CampaignDto.builder()
                    .id(1L)
                    .name("AdSet_from_server1")
                    .impressionsHistory(asList(10L, 20L))
                    .clicksHistory(asList(10L, 20L))
                    .costHistory(asList(10L, 20L))
                    .currentValue(100L)
                    .build(),
            CampaignDto.builder()
                    .id(2L)
                    .name("AdSet_from_server2")
                    .impressionsHistory(asList(10L, 20L))
                    .clicksHistory(asList(10L, 20L))
                    .costHistory(asList(10L, 20L))
                    .build(),
            CampaignDto.builder()
                    .id(3L)
                    .name("AdSet_from_server3")
                    .impressionsHistory(asList(10L, 20L))
                    .clicksHistory(asList(10L, 20L))
                    .costHistory(asList(10L, 20L))
                    .build()
    );

    public List<CampaignDto> findAll() {
        final List<CampaignDto> result = new ArrayList<>();
        result.addAll(campaignDtos);

        return result;
    }

    public CampaignDto findById(final long id) {
        return campaignDtos.stream().filter(c -> c.getId() == id).findFirst().get();
    }
}
