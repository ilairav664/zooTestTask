package com.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductPeriodDTO {
    String periodStart;
    String periodEnd;
    List<CountProductsDTO> products;
}
