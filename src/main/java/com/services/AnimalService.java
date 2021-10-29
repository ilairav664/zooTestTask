package com.services;

import com.config.ObjectMapper;
import com.dto.AnimalDTO;
import com.dto.NewNormDTO;
import com.dto.NormDTO;
import com.entities.Animal;
import com.entities.Norm;
import com.entities.Product;
import com.entities.Type;
import com.repositories.AnimalRepository;
import com.repositories.NormRepository;
import com.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    AnimalRepository animalRepository;
    ProductRepository productRepository;
    NormRepository normRepository;
    ObjectMapper objectMapper;
    @Autowired
    public AnimalService (AnimalRepository animalRepository,
                          NormRepository normRepository,
                          ProductRepository productRepository,
                          ObjectMapper objectMapper){
    this.animalRepository = animalRepository;
    this.productRepository = productRepository;
    this.normRepository = normRepository;
    this.objectMapper = objectMapper;
}

    public List<Object> getAnimals(){
        return animalRepository.findAll().stream().map(item->objectMapper.mapToDTO(item)).collect(Collectors.toList());
    }
    public Object getAnimal(Integer id){
        Optional<Animal> find = animalRepository.findById(id);
        return find.map(animal -> objectMapper.mapToDTO(animal)).orElse(null);
    }
    public List<AnimalDTO> getAnimalsWithProducts(Integer page, Integer perPage,String species, Type productType,Boolean isPredator,String animalName){
        Pageable pageable = PageRequest.of(page, perPage);
        return animalRepository.findAllWithParams(species,productType,isPredator,animalName,pageable)
                .stream().map(item->objectMapper.mapWithProducts(item)).collect(Collectors.toList());
    }
    public Object addAnimal(AnimalDTO animalDTO){
        return objectMapper.mapToDTO(animalRepository.save(objectMapper.mapFromDTO(animalDTO)));
    }
    public void deleteAnimal(Integer id){
        animalRepository.deleteById(id);
    }
    public List<Object> addNorm(List<NewNormDTO> newNormDTOList){
        List <Object> results = new ArrayList<>();
        newNormDTOList.forEach(item->{
            Norm norm = new Norm();
            Optional<Animal> animal = animalRepository.findById(item.getAnimalId());
            Optional<Product> product = productRepository.findById(item.getProductId());
            animal.ifPresent(norm::setAnimal);
            product.ifPresent(norm::setProduct);
            results.add(objectMapper.mapToDTO(normRepository.save(norm)));
        });
        return results;
    }
}
