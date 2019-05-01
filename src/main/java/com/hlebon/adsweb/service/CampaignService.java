package com.hlebon.adsweb.service;

import com.hlebon.adsweb.repository.dao.CampaignDao;
import com.hlebon.adsweb.repository.dao.CampaignStatisticDao;
import com.hlebon.adsweb.repository.dao.CampaignStatisticDayDao;
import com.hlebon.adsweb.repository.dao.ProductDao;
import com.hlebon.adsweb.repository.dao.ProductStatisticDao;
import com.hlebon.adsweb.repository.entity.CampaignEntity;
import com.hlebon.adsweb.repository.entity.CampaignStatisticDayEntity;
import com.hlebon.adsweb.repository.entity.ProductEntity;
import com.hlebon.adsweb.repository.entity.ProductStatisticsEntity;
import com.hlebon.adsweb.service.mapper.CampaignMapper;
import com.hlebon.adsweb.service.mapper.CampaignStatisticMapper;
import com.hlebon.adsweb.service.searchable.CampaignSearchableObject;
import com.hlebon.adsweb.service.searchable.CampaignSearchableWithTypeObject;
import com.hlebon.adsweb.service.searchable.PageRequestObject;
import com.hlebon.adsweb.web.dto.CampaignDto;
import com.hlebon.adsweb.web.dto.CampaignListDto;
import com.hlebon.adsweb.web.dto.PageableDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignDao campaignDao;
    private final CampaignStatisticDao campaignStatisticDao;
    private final CampaignStatisticDayDao campaignStatisticDayDao;
    private final ProductDao productDao;
    private final ProductStatisticDao productStatisticDao;

    private final CampaignMapper campaignMapper;
    private final CampaignStatisticMapper campaignStatisticMapper;

    @Transactional(readOnly = true)
    public List<CampaignDto> findAll(final CampaignSearchableObject campaignSearchableObject) {
        final PageRequestObject pageRequestObject = campaignSearchableObject.getPageRequest();
        final PageRequest pageRequest;
        if (pageRequestObject.getSort() == null) {
            pageRequest = PageRequest.of(pageRequestObject.getPage() - 1, pageRequestObject.getSize());
        } else {
            final Sort.Direction direction;
            if (pageRequestObject.getOrder().equals("ASC")) {
                direction = Sort.Direction.ASC;
            } else {
                direction = Sort.Direction.DESC;
            }
            pageRequest = PageRequest.of(pageRequestObject.getPage() - 1, pageRequestObject.getSize(), Sort.by(new Sort.Order(direction, pageRequestObject.getSort())));
        }


        final Page<CampaignEntity> allCampaigns = campaignDao.findAll(pageRequest);
        final List<CampaignDto> result = allCampaigns.stream().map(campaignMapper::mapToDto).collect(toList());
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

    public CampaignStatistic findValueByPeriod(final long id, final CampaignSearchableWithTypeObject campaignObject) {
        final Optional<CampaignEntity> campaign = campaignDao.findById(id);
        if (!campaign.isPresent()) {
            return null;
        }
        final CampaignEntity campaignEntity = campaign.get();
        final CampaignDto campaignDto = campaignMapper.mapToDto(campaignEntity);
        final CampaignStatistic result = new CampaignStatistic(campaignDto.getId(), campaignDto.getName());
        if (campaignObject.isFetchStatistics()) {
            final PeriodType type = campaignObject.getType();
            final LocalDate startDate = campaignObject.getStartDate();
            final LocalDate endDate = campaignObject.getEndDate() != null ? campaignObject.getEndDate() : LocalDate.now();
            final List<CampaignStatisticDayEntity> statistics =
                    campaignStatisticDayDao.findByCampaign_IdAndDateStatisticBetweenOrderByDateStatistic(result.getId(), startDate, endDate);

            final List<CampaignStatistic.Statistic.CampaignStatisticDto> campaignStatistics;
            switch (type) {
                case DAY: {
                    campaignStatistics = statistics.stream().map(s -> {
                        final Long impressions = s.getImpressions();
                        final Long clicks = s.getClicks();
                        final Long spends = s.getSpends();
                        final LocalDate date = s.getDateStatistic();
                        final CampaignStatistic.Statistic.CampaignStatisticDto.DayPeriod period = new CampaignStatistic.Statistic.CampaignStatisticDto.DayPeriod(date);
                        return new CampaignStatistic.Statistic.CampaignStatisticDto(impressions, clicks, spends, period);
                    }).collect(toList());
                    break;
                }
                case WEEK: {
                    campaignStatistics = new ArrayList<>();
                    final Map<Integer, Map<Integer, List<CampaignStatisticDayEntity>>> weekMap = statistics
                            .stream()
                            .sorted(Comparator.comparing(CampaignStatisticDayEntity::getYear))
                            .collect(groupingBy(
                                    CampaignStatisticDayEntity::getYear,
                                    groupingBy(
                                            CampaignStatisticDayEntity::getWeekNumber, toList()
                                    )
                            ));

                    for (Map.Entry<Integer, Map<Integer, List<CampaignStatisticDayEntity>>> currentYear : weekMap.entrySet()) {
                        final Integer year = currentYear.getKey();
                        final Map<Integer, List<CampaignStatisticDayEntity>> currentWeek = currentYear.getValue();
                        List<CampaignStatistic.Statistic.CampaignStatisticDto> currentYearStatistic = currentWeek.entrySet().stream().map(entry -> {
                            final Integer weekNumber = entry.getKey();
                            final List<CampaignStatisticDayEntity> stats = entry.getValue();
                            long impressions = 0;
                            long clicks = 0;
                            long spends = 0;
                            for (CampaignStatisticDayEntity stat : stats) {
                                impressions += stat.getImpressions();
                                clicks += stat.getClicks();
                                spends += stat.getSpends();
                            }
                            final CampaignStatistic.Statistic.CampaignStatisticDto.WeekPeriod weekPeriod = new CampaignStatistic.Statistic.CampaignStatisticDto.WeekPeriod(weekNumber, year);
                            return new CampaignStatistic.Statistic.CampaignStatisticDto(impressions, clicks, spends, weekPeriod);
                        }).sorted(Comparator.comparing(c -> c.getPeriod().getPeriod())).collect(toList());
                        campaignStatistics.addAll(currentYearStatistic);
                    }
                    break;
                }
                case MONTH: {
                    campaignStatistics = new ArrayList<>();
                    final Map<Integer, Map<Integer, List<CampaignStatisticDayEntity>>> monthMap = statistics
                            .stream()
                            .sorted(Comparator.comparing(CampaignStatisticDayEntity::getYear))
                            .collect(groupingBy(
                                    CampaignStatisticDayEntity::getYear,
                                    groupingBy(
                                            CampaignStatisticDayEntity::getMonth, toList()
                                    )
                            ));

                    for (Map.Entry<Integer, Map<Integer, List<CampaignStatisticDayEntity>>> currentYear : monthMap.entrySet()) {
                        final Integer year = currentYear.getKey();
                        final Map<Integer, List<CampaignStatisticDayEntity>> currentMonth = currentYear.getValue();
                        List<CampaignStatistic.Statistic.CampaignStatisticDto> currentYearStatistic = currentMonth.entrySet().stream().map(entry -> {
                            final Integer monthNumber = entry.getKey();
                            final List<CampaignStatisticDayEntity> stats = entry.getValue();
                            long impressions = 0;
                            long clicks = 0;
                            long spends = 0;
                            for (CampaignStatisticDayEntity stat : stats) {
                                impressions += stat.getImpressions();
                                clicks += stat.getClicks();
                                spends += stat.getSpends();
                            }
                            final CampaignStatistic.Statistic.CampaignStatisticDto.MontnPeriod monthPeriod = new CampaignStatistic.Statistic.CampaignStatisticDto.MontnPeriod(monthNumber, year);
                            return new CampaignStatistic.Statistic.CampaignStatisticDto(impressions, clicks, spends, monthPeriod);
                        }).sorted(Comparator.comparing(c -> c.getPeriod().getPeriod())).collect(toList());
                        campaignStatistics.addAll(currentYearStatistic);
                    }
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unsupported type " + type);
            }

            final CampaignStatistic.Statistic statistic = new CampaignStatistic.Statistic(type, campaignStatistics);
            result.setStatistic(statistic);
        }
        return result;

    }

    @Transactional(readOnly = true)
    public CampaignListDto findPage(final CampaignSearchableObject campaignSearchableObject) {
        final PageRequestObject pageRequestObject = campaignSearchableObject.getPageRequest();
        final PageRequest pageRequest;
        if (pageRequestObject.getSort() == null) {
            pageRequest = PageRequest.of(pageRequestObject.getPage() - 1, pageRequestObject.getSize());
        } else {
            final Sort.Direction direction;
            if (pageRequestObject.getOrder().equals("ASC")) {
                direction = Sort.Direction.ASC;
            } else {
                direction = Sort.Direction.DESC;
            }
            pageRequest = PageRequest.of(pageRequestObject.getPage() - 1, pageRequestObject.getSize(), Sort.by(new Sort.Order(direction, pageRequestObject.getSort())));
        }


        final Page<CampaignEntity> allCampaigns = campaignDao.findAll(pageRequest);
        final List<CampaignDto> campaignDtos = allCampaigns.stream().map(campaignMapper::mapToDto).collect(toList());
        if (campaignSearchableObject.isFetchStatistics()) {
            campaignDtos.forEach(this::fillStatistics);
            if (campaignSearchableObject.isFetchProductStatistic()) {
                campaignDtos.forEach(c -> {
                    final Set<CampaignDto.CampaignStatisticDto> campaignStatisticDtos = c.getCampaignStatistics();
                    final ProductEntity product = productDao.findByCampaign_Id(c.getId());
                    fillStatistics(campaignStatisticDtos, product.getId());
                });
            }
        }

        final CampaignListDto result = new CampaignListDto();
        allCampaigns.getTotalElements();
        result.setCampaigns(campaignDtos);
        result.setPageable(new PageableDto(
                allCampaigns.getTotalPages(),
                campaignSearchableObject.getPageRequest().getPage(),
                campaignSearchableObject.getPageRequest().getSize()
        ));

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
