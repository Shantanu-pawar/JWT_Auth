package com.SpringJWT_auth.Service;

import com.SpringJWT_auth.Entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {


    private List<Product> list = new ArrayList<>();

    public ProductService() {
        list.add(new Product(UUID.randomUUID().toString(), "Atlatian", 234));
        list.add(new Product(UUID.randomUUID().toString(), "dasult systems", 343412));
        list.add(new Product(UUID.randomUUID().toString(), "Accenture", 343400));
    }

    public List<Product> getList(){
        return list;
    }

    public Product getProduct(String name){
        return list.stream()
                .filter(product -> product.getName() == name)
                .findAny()
                .orElseThrow(()-> new RuntimeException("product : " + name + " is not found"));
    }

}
