package com.dto;

import com.entities.Type;
import com.entities.Units;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductWithNormDTO {
    String name;
    Type type;
    Integer consumption;
    Units units;
}
