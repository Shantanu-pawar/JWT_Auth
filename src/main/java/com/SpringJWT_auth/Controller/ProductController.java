package com.SpringJWT_auth.Controller;

import com.SpringJWT_auth.Entities.Product;
import com.SpringJWT_auth.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    /*Refer this documentation for JWT auth */

    @Autowired
    ProductService service;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome but this endpoint is not secured";
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){

        return service.getList();
    }

    @GetMapping("/{id}")
    public Product getProductById (@PathVariable("id") String name){
        return  service.getProduct(name);
    }

}
