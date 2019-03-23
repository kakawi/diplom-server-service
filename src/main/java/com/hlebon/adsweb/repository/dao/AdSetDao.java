package com.hlebon.adsweb.repository.dao;

import com.hlebon.adsweb.repository.entity.AdSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AdSetDao extends JpaRepository<AdSetEntity, Long>, JpaSpecificationExecutor<AdSetEntity> {
}
