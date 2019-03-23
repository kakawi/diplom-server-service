package com.hlebon.adsweb.repository.dao;

import com.hlebon.adsweb.repository.entity.AdSetStatisticEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdSetStatisticDao extends JpaRepository<AdSetStatisticEntity, Long> {
    List<AdSetStatisticEntity> findByAdSet_Id(Long campaignId, Pageable pageable);
}
