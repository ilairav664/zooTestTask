package com.dto;

import com.entities.Type;
import com.entities.Units;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductDTO {

    private Integer id;

    private String name;

    private int count;

    private Units units;

    private Type type;

}
