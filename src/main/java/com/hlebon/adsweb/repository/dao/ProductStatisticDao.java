package com.hlebon.adsweb.repository.dao;

import com.hlebon.adsweb.repository.entity.ProductStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatisticDao extends JpaRepository<ProductStatisticsEntity, Long> {
    ProductStatisticsEntity findByProduct_IdAndWeekNumberAndYear(Long productId, int weekNumber, int year);
}
