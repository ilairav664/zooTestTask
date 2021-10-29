package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "units", nullable = false)
    private Units units;

    @Column(name = "type",nullable = false)
    private Type type;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    List<Norm> normList = new ArrayList<>();

    public Integer countProductPerDay(){
        AtomicReference<Integer> sum = new AtomicReference<>(0);
        normList.forEach(item->{
            sum.set(sum.get() + item.getPerDay());
        });
        return sum.get();
    }
}
