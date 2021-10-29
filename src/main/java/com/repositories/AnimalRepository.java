package com.repositories;

import com.entities.Animal;
import com.entities.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnimalRepository extends PagingAndSortingRepository<Animal, Integer> {
    @Query("SELECT a FROM Animal a")
    List<Animal> findAll();
    @Query("SELECT a FROM Animal a join fetch a.normList n where " +
            "(:species is NULL or a.species=:species) and " +
            "(:animalName is null or a.name=:animalName) and"+
            "(:isPredator is null or a.isPredator=:isPredator) and " +
            "(:productType is NULL or n.product.type=:productType)")
    List<Animal> findAllWithParams(
                                     @Param("species") String species,
                                     @Param("productType") Type productType,
                                     @Param("isPredator") Boolean isPredator,
                                     @Param("animalName") String animalName,
                                     Pageable pageable
    );
}