package com.hlebon.adsweb.service;

import com.hlebon.adsweb.repository.dao.AdSetDao;
import com.hlebon.adsweb.repository.dao.AdSetStatisticDao;
import com.hlebon.adsweb.repository.entity.AdSetEntity;
import com.hlebon.adsweb.service.mapper.AdSetMapper;
import com.hlebon.adsweb.service.mapper.AdSetStatisticMapper;
import com.hlebon.adsweb.service.searchable.AdSetMetadataObject;
import com.hlebon.adsweb.service.searchable.AdSetSearchableObject;
import com.hlebon.adsweb.web.dto.AdSetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdSetService {
    private final AdSetDao adSetDao;
    private final AdSetStatisticDao adSetStatisticDao;
    private final AdSetMapper adSetMapper;
    private final AdSetStatisticMapper adSetStatisticMapper;

    private static Specification<AdSetEntity> belongsToCampaign(final Long campaignId) {
        return (Specification<AdSetEntity>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("campaign").get("id"), campaignId);
    }

    @Transactional(readOnly = true)
    public List<AdSetDto> findAll(final AdSetSearchableObject adSetSearchableObject, final AdSetMetadataObject adSetMetadataObject) {
        final List<AdSetEntity> allAdSets = getFromDao(adSetSearchableObject);
        final List<AdSetDto> result = allAdSets.stream().map(adSetMapper::mapToDto).collect(Collectors.toList());
        if (adSetMetadataObject.isFetchStatistics()) {
            result.forEach(a -> a.setAdSetStatistics(fillStatistics(a)));
        }
        return result;
    }

    private List<AdSetEntity> getFromDao(final AdSetSearchableObject adSetSearchableObject) {
        if (adSetSearchableObject.getCampaign() != null && adSetSearchableObject.getCampaign().getId() != null) {
            final Long campaignId = adSetSearchableObject.getCampaign().getId();
            return adSetDao.findAll(belongsToCampaign(campaignId));
        }
        return adSetDao.findAll();
    }

    @Transactional(readOnly = true)
    public AdSetDto findById(final long id, final AdSetMetadataObject adSetSearchableObject) {
        final Optional<AdSetEntity> adSet = adSetDao.findById(id);
        if (!adSet.isPresent()) {
            return null;
        }
        final AdSetEntity adSetEntity = adSet.get();
        final AdSetDto result = adSetMapper.mapToDto(adSetEntity);
        if (adSetSearchableObject.isFetchStatistics()) {
            result.setAdSetStatistics(fillStatistics(result));
        }
        return result;
    }

    private Set<AdSetDto.AdSetStatisticDto> fillStatistics(final AdSetDto adSetDto) {
        final Pageable pageable = PageRequest.of(0, 6, Sort.by("year").descending().and(Sort.by("weekNumber").descending()));
        return adSetStatisticDao
                .findByAdSet_Id(adSetDto.getId(), pageable)
                .stream()
                .map(adSetStatisticMapper::mapToDto)
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(AdSetDto.AdSetStatisticDto::getWeekNumber)))
                );
    }

}
