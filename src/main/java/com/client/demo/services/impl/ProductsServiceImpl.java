package com.client.demo.services.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.client.demo.models.Products;
import com.client.demo.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final WebClient webClient;
    @Autowired
    public ProductsServiceImpl(WebClient webClient){
        this.webClient = webClient;
    }

    public List<Products> getAllProducts(){
        Mono<Products> productsMono = webClient.get()
                .uri("/inventario/producto/{id}", 4)
                .retrieve()
                .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(),
                        clientResponse -> handleErrorResponse(clientResponse.statusCode()))
                .bodyToMono(Products.class);

        productsMono.subscribe(products -> {
            System.out.println("llego el producto"+ ' ' +products);
        }, error -> {
            System.out.println(error.getMessage());
        });

        return null;
    }

    private Mono<? extends Throwable> handleErrorResponse(HttpStatusCode statusCode){
        return Mono.error(new Exception("Todos vamos a morir by Zorman"));
    }
}
