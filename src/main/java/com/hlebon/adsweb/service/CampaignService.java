package com.hlebon.adsweb.service;

import com.hlebon.adsweb.repository.dao.CampaignDao;
import com.hlebon.adsweb.repository.entity.CampaignEntity;
import com.hlebon.adsweb.web.dto.CampaignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignDao campaignDao;

    private List<CampaignDto> campaignDtos = asList(
    );

    public List<CampaignEntity> findAll() {
        final List<CampaignEntity> result = campaignDao.findAll();

        return result;
    }

    public CampaignDto findById(final long id) {
        return campaignDtos.stream().filter(c -> c.getId() == id).findFirst().get();
    }
}
