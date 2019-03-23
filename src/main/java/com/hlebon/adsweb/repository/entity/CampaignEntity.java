package com.hlebon.adsweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "campaign")
@Getter
@Setter
public class CampaignEntity {
    @Id
    private Long id;

    private String name;
}
