package com.client.demo.controllers;

import com.client.demo.models.Products;
import com.client.demo.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/inventario")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @GetMapping("/productos")
    public List<Products> productsModels(){
        return productsService.getAllProducts();
    }


}
