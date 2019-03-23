package com.hlebon.adsweb.repository.dao;

import com.hlebon.adsweb.repository.entity.CampaignStatisticEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignStatisticDao extends JpaRepository<CampaignStatisticEntity, Long> {
    List<CampaignStatisticEntity> findByCampaign_Id(Long campaignId, Pageable pageable);
}
