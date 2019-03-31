package com.hlebon.adsweb.repository.dao;

import com.hlebon.adsweb.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByCampaign_Id(long campaignId);
}
