package com.hlebon.adsweb.repository.dao;

import com.hlebon.adsweb.repository.entity.CampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignDao extends JpaRepository<CampaignEntity, Long> {
}
