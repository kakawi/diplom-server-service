package com.hlebon.adsweb.repository.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ad", schema = "diplom", catalog = "gleb")
public class AdEntity {
    private Long id;
    private String name;

    @Basic
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public _Dummy_ setId(Long id) {
        this.id = id;
        return this;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public _Dummy_ setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdEntity adEntity = (AdEntity) o;
        return Objects.equals(id, adEntity.id) &&
                Objects.equals(name, adEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
