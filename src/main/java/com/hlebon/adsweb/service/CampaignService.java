package com.hlebon.adsweb.service;

import com.hlebon.adsweb.repository.dao.CampaignDao;
import com.hlebon.adsweb.repository.dao.CampaignStatisticDao;
import com.hlebon.adsweb.repository.dao.ProductDao;
import com.hlebon.adsweb.repository.dao.ProductStatisticDao;
import com.hlebon.adsweb.repository.entity.CampaignEntity;
import com.hlebon.adsweb.repository.entity.ProductEntity;
import com.hlebon.adsweb.repository.entity.ProductStatisticsEntity;
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

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignDao campaignDao;
    private final CampaignStatisticDao campaignStatisticDao;
    private final ProductDao productDao;
    private final ProductStatisticDao productStatisticDao;

    private final CampaignMapper campaignMapper;
    private final CampaignStatisticMapper campaignStatisticMapper;

    @Transactional(readOnly = true)
    public List<CampaignDto> findAll(final CampaignSearchableObject campaignSearchableObject) {
        final List<CampaignEntity> allCampaigns = campaignDao.findAll();
        final List<CampaignDto> result = allCampaigns.stream().map(campaignMapper::mapToDto).collect(Collectors.toList());
        if (campaignSearchableObject.isFetchStatistics()) {
            result.forEach(this::fillStatistics);
            if (campaignSearchableObject.isFetchProductStatistic()) {
                result.forEach(c -> {
                    final Set<CampaignDto.CampaignStatisticDto> campaignStatisticDtos = c.getCampaignStatistics();
                    final ProductEntity product = productDao.findByCampaign_Id(c.getId());
                    fillStatistics(campaignStatisticDtos, product.getId());
                });
            }
        }

        return result;
    }

    @Transactional(readOnly = true)
    public CampaignDto findById(final long id, final CampaignSearchableObject campaignSearchableObject) {
        final Optional<CampaignEntity> campaign = campaignDao.findById(id);
        if (!campaign.isPresent()) {
            return null;
        }
        final CampaignEntity campaignEntity = campaign.get();
        final CampaignDto result = campaignMapper.mapToDto(campaignEntity);
        if (campaignSearchableObject.isFetchStatistics()) {
            fillStatistics(result);
            if (campaignSearchableObject.isFetchProductStatistic()) {
                final Set<CampaignDto.CampaignStatisticDto> campaignStatisticDtos = result.getCampaignStatistics();
                final ProductEntity product = productDao.findByCampaign_Id(result.getId());
                fillStatistics(campaignStatisticDtos, product.getId());
            }
        }
        return result;
    }

    private void fillStatistics(final CampaignDto campaignDto) {
        final Pageable pageable = PageRequest.of(0, 6, Sort.by("year").descending().and(Sort.by("weekNumber").descending()));
        final TreeSet<CampaignDto.CampaignStatisticDto> statistic = campaignStatisticDao
                .findByCampaign_Id(campaignDto.getId(), pageable)
                .stream()
                .map(campaignStatisticMapper::mapToDto)
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(CampaignDto.CampaignStatisticDto::getWeekNumber)))
                );
        campaignDto.setCampaignStatistics(statistic);
    }

    private void fillStatistics(Collection<CampaignDto.CampaignStatisticDto> campaignStatisticDtos, final Long productId) {
        campaignStatisticDtos.forEach(s -> {
            final ProductStatisticsEntity productStatistic = productStatisticDao.findByProduct_IdAndWeekNumberAndYear(productId, s.getWeekNumber(), s.getYear());
            if (productStatistic != null) {
                s.setSells(productStatistic.getSell());
            }
        });
    }

}
