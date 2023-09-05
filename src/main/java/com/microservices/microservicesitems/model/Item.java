package com.microservices.microservicesitems.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    private Producto producto;
    private Integer cantidad;


    public Double getTotal(){
        return producto.getPrecio() * cantidad.doubleValue();
    }

}
