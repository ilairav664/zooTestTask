package com.dto;

import com.entities.Animal;
import com.entities.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class NormDTO {

    private Integer id;

    Animal animal;

    Product product;

    Integer perDay;
}
