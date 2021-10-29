package com.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "norm")
public class Norm {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    Animal animal;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    Product product;

    @Column(name = "perDay")
    Integer perDay;

}
