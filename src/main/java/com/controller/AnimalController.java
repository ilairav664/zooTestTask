package com.controller;

import com.dto.AnimalDTO;
import com.dto.NewNormDTO;
import com.dto.NormDTO;
import com.entities.Type;
import com.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    public AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService){
        this.animalService = animalService;
    }

    @GetMapping("/getAnimals")
    public List<Object> getAnimals(){
        return animalService.getAnimals();
    }
    @GetMapping("/{id}")
    public Object getAnimal(@PathVariable Integer id){
        return animalService.getAnimal(id);
    }

    @RequestMapping(value="/getWithProducts" , method= RequestMethod.GET)
    public Object getAnimalWithProducts(@RequestParam(value = "species",required = false) String species,
                                        @RequestParam(value = "productType",required = false)Type productType,
                                        @RequestParam(value = "isPredator",required = false) Boolean isPredator,
                                        @RequestParam(value = "animalName",required = false) String animalName,
                                        @RequestParam(value = "page") Integer page,
                                        @RequestParam(value = "perPage") Integer perPage){

        return animalService.getAnimalsWithProducts(page,perPage,species,productType,isPredator,animalName);
    }
    @PostMapping("/add")
    public Object addAnimal(
            @RequestBody AnimalDTO animalDTO) {
        return animalService.addAnimal(animalDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Integer id){
        animalService.deleteAnimal(id);
    }
    @RequestMapping(value="/setNewNorm" , method= RequestMethod.GET)
    public Object setNewNorm(@RequestBody List<NewNormDTO> newNormDTO) {
        return animalService.addNorm(newNormDTO);
    }


}
