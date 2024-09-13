package com.client.demo.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Products {

    private Long id;
    private String name;
    private double price;
    private LocalDate date;

}
