package com.hlebon.adsweb.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CampaignEntity {
    @Id
    private Long id;

    private String name;
}
