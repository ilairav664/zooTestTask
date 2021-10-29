package com.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "species", nullable = false)
    private String species;

    @Column(name = "isPredator",nullable = false)
    private Boolean isPredator;

    @OneToMany(mappedBy = "animal", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Norm> normList = new ArrayList<>();

}
