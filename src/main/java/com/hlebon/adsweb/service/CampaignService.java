package com.hlebon.adsweb.service;

import com.hlebon.adsweb.repository.dao.CampaignDao;
import com.hlebon.adsweb.repository.dao.CampaignStatisticDao;
import com.hlebon.adsweb.repository.entity.CampaignEntity;
import com.hlebon.adsweb.repository.entity.CampaignStatisticEntity;
import com.hlebon.adsweb.service.mapper.CampaignMapper;
import com.hlebon.adsweb.service.mapper.CampaignStatisticMapper;
import com.hlebon.adsweb.service.searchable.CampaignSearchableObject;
import com.hlebon.adsweb.web.dto.CampaignDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignDao campaignDao;
    private final CampaignStatisticDao campaignStatisticDao;
    private final CampaignMapper campaignMapper;
    private final CampaignStatisticMapper campaignStatisticMapper;

    private List<CampaignDto> campaignDtos = asList(
    );

    @Transactional(readOnly = true)
    public List<CampaignEntity> findAll() {
        final List<CampaignEntity> result = campaignDao.findAll();

        return result;
    }

    public List<CampaignDto> findAll(final CampaignSearchableObject campaignSearchableObject) {
        final List<CampaignEntity> allCampaigns = campaignDao.findAll();
        final List<CampaignDto> result = allCampaigns.stream().map(campaignMapper::mapToDto).collect(Collectors.toList());
        if (campaignSearchableObject.isFetchStatistics()) {
            fillStatistics(result);
        }
        return result;
    }

    private void fillStatistics(final List<CampaignDto> campaignDtos) {
        final Pageable pageable = PageRequest.of(0, 6, Sort.by("year").descending().and(Sort.by("weekNumber").descending()));
        for (final CampaignDto campaign : campaignDtos) {
            final List<CampaignStatisticEntity> campaignStatistics = campaignStatisticDao.findByCampaign_Id(campaign.getId(), pageable);
            campaign.setCampaignStatistics(campaignStatistics
                    .stream()
                    .map(campaignStatisticMapper::mapToDto)
                    .collect(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparing(CampaignDto.CampaignStatisticDto::getWeekNumber)))
                    )
            );
        }
    }

    public CampaignDto findById(final long id) {
        return campaignDtos.stream().filter(c -> c.getId() == id).findFirst().get();
    }

}
