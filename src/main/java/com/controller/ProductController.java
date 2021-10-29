package com.controller;

import com.dto.AnimalDTO;
import com.dto.CountProductsDTO;
import com.dto.ProductDTO;
import com.dto.ProductPeriodDTO;
import com.entities.Product;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/getProducts")
    public List<Object> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/{id}")
    public Object getProduct(@PathVariable Integer id){
        return productService.getProduct(id);
    }
    @RequestMapping(value="/countProducts" , method= RequestMethod.GET)
    public ProductPeriodDTO countProducts(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate) {
        return productService.countProducts(fromDate);
         }

    @PostMapping("/add")
    public Object addProduct(
            @RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }
    @RequestMapping(value="/changeProductCount" , method= RequestMethod.GET)
    public Object setNewNorm(@RequestParam(value = "productId") Integer productId,
                             @RequestParam(value = "count") Integer count) {
        return productService.changeProductCount(productId,count);
    }
}
