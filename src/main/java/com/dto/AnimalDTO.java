package com.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AnimalDTO {

    private Integer id;

    private String name;

    private String species;

    private Boolean isPredator;

    private List<ProductWithNormDTO> products;

}
