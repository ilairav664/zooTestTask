package com.config;

import com.dto.*;
import com.entities.Animal;
import com.entities.Norm;
import com.entities.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ObjectMapper {
    public Object mapToDTO(Object object) {
        if (object instanceof Animal) {
            return map((Animal) object);
        }
        if (object instanceof Product) {
            return map((Product) object);
        }
        if (object instanceof Norm) {
            return map((Norm) object);
        }
        return null;
    }

    public Animal mapFromDTO(AnimalDTO animalDTO){
        Animal animal = new Animal();
        animal.setName(animalDTO.getName());
        animal.setSpecies(animalDTO.getSpecies());
        animal.setIsPredator(animalDTO.getIsPredator());
        return animal;
    }
    public Product mapFromDTO(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCount(productDTO.getCount());
        product.setUnits(productDTO.getUnits());
        product.setType(productDTO.getType());
        return product;
    }
    public CountProductsDTO mapToCountDTO(Product product){
        CountProductsDTO dto = new CountProductsDTO();
        dto.setProductName(product.getName());
        dto.setCurrentCount(product.getCount());
        dto.setUnits(product.getUnits());
        dto.setProductNeed(product.countProductPerDay()*7);
        if((product.getCount()-product.countProductPerDay()*7)<0){
            dto.setDeficiency(Math.abs(product.getCount()-product.countProductPerDay()*7));
        }else {
            dto.setDeficiency(null);
        }
        return dto;
    }
    public AnimalDTO mapWithProducts(Animal animal){
        AnimalDTO dto = new AnimalDTO();
        dto.setName(animal.getName());
        dto.setSpecies(animal.getSpecies());
        dto.setProducts(animal.getNormList().stream().map(this::mapWithNorm).collect(Collectors.toList()));
        return dto;
    }
    public ProductWithNormDTO mapWithNorm(Norm norm){
        ProductWithNormDTO product = new ProductWithNormDTO();
        product.setName(norm.getProduct().getName());
        product.setType(norm.getProduct().getType());
        product.setUnits(norm.getProduct().getUnits());
        product.setConsumption(norm.getPerDay());
        return product;
    }
    private AnimalDTO map(Animal animal){
        AnimalDTO animalDTO = new AnimalDTO();
        animalDTO.setId(animal.getId());
        animalDTO.setName(animal.getName());
        animalDTO.setIsPredator(animal.getIsPredator());
        animalDTO.setSpecies(animal.getSpecies());
        return animalDTO;
    }
    private ProductDTO map(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCount(product.getCount());
        productDTO.setType(product.getType());
        productDTO.setUnits(product.getUnits());
        return productDTO;
    }

    private NormDTO map(Norm norm){
        NormDTO normDTO = new NormDTO();
        normDTO.setId(norm.getId());
        normDTO.setAnimal(norm.getAnimal());
        normDTO.setProduct(norm.getProduct());
        normDTO.setPerDay(norm.getPerDay());
        return normDTO;
    }
}
