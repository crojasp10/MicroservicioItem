package com.microservices.microservicesitems.model;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {

    private long id;
    private String nombre;
    private Double precio;
    private Date createAt;
}
