package com.hlebon.adsweb.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "product_statistics")
@Getter
@Setter
public class ProductStatisticsEntity {
    @Id
    private Long id;
    private Long sell;
    private Integer weekNumber;
    private Integer year;

    @ManyToOne
    private ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductStatisticsEntity that = (ProductStatisticsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(sell, that.sell) &&
                Objects.equals(weekNumber, that.weekNumber) &&
                Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sell, weekNumber, year);
    }
}
