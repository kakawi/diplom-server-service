package com.hlebon.adsweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ad_set")
@Getter
@Setter
public class AdSetEntity {
    @Id
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private CampaignEntity campaign;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdSetEntity that = (AdSetEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
