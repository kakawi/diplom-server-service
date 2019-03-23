package com.hlebon.adsweb.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "campaign")
public class CampaignEntity {
    @Id
    private Long id;

    private String name;
}
