package com.services;

import com.config.ObjectMapper;
import com.dto.CountProductsDTO;
import com.dto.ProductDTO;
import com.dto.ProductPeriodDTO;
import com.entities.Product;
import com.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    ProductRepository productRepository;
    ObjectMapper objectMapper;
    @Autowired
    public ProductService(ProductRepository productRepository, ObjectMapper objectMapper){
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }
    public List<Object> getProducts(){
        return ((List<Product>)productRepository.findAll()).stream().map(item->objectMapper.mapToDTO(item)).collect(Collectors.toList());
    }
    public Object getProduct(Integer id){
        Optional<Product> find = productRepository.findById(id);
        return find.map(product -> objectMapper.mapToDTO(product)).orElse(null);
    }
    public ProductPeriodDTO countProducts(Date fromDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        ProductPeriodDTO productPeriodDTO = new ProductPeriodDTO();
        List<CountProductsDTO> product = new ArrayList<>(((List<Product>) productRepository.findAll())
                .stream().map(item -> objectMapper.mapToCountDTO(item)).collect(Collectors.toList()));
        productPeriodDTO.setProducts(product);
        productPeriodDTO.setPeriodStart(dateFormat.format(fromDate));
        productPeriodDTO.setPeriodEnd(dateFormat.format(addDays(fromDate,7)));
        return productPeriodDTO;
    }

    public Object addProduct(ProductDTO productDTO){
        return objectMapper.mapToDTO(productRepository.save(objectMapper.mapFromDTO(productDTO)));
    }
    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }
    public Object changeProductCount(Integer productId,Integer newCount){
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()){
            Product exist = product.get();
            exist.setCount(newCount);
            productRepository.save(exist);
            return objectMapper.mapToDTO(exist);
        }
        return null;
    }
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

}
