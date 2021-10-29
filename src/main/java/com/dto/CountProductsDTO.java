package com.dto;

import com.entities.Units;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CountProductsDTO {
    String productName;
    Integer productNeed;
    Integer currentCount;
    Units units;
    Integer deficiency;
}
