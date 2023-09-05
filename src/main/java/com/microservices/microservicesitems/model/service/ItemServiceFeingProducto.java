package com.microservices.microservicesitems.model.service;

import com.microservices.microservicesitems.model.Item;
import com.microservices.microservicesitems.model.Producto;
import com.microservices.microservicesitems.model.clientesFeign.ProductoClienteRest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeignProducto")
public class ItemServiceFeingProducto implements ItemService{

    @Autowired
    private ProductoClienteRest clienteFeingProducto;


    @Override
    public List<Item> findAll() {
        return clienteFeingProducto.listarProductos().stream().map(p->
            new Item(p,2)).collect(Collectors.toList());
    }

    @Override

    public Item findById(Long id, Integer cantidad) {
        return new Item(clienteFeingProducto.detalleProducto(id),cantidad);
    }


}
