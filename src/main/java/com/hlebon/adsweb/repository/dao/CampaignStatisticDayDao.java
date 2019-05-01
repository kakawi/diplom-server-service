package com.hlebon.adsweb.repository.dao;

import com.hlebon.adsweb.repository.entity.CampaignStatisticDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampaignStatisticDayDao extends JpaRepository<CampaignStatisticDayEntity, Long> {
//    @Query("select c from CampaignStatisticDayEntity c where c.campaign.id = :campaignId and c.dateStatistic")
    List<CampaignStatisticDayEntity> findByCampaign_IdAndDateStatisticBetweenOrderByDateStatistic(Long campaignId, LocalDate startDate, LocalDate endDate);
}
