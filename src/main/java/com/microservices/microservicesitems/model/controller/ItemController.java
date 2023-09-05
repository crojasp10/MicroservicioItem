package com.microservices.microservicesitems.model.controller;

import com.microservices.microservicesitems.model.Item;
import com.microservices.microservicesitems.model.Producto;
import com.microservices.microservicesitems.model.service.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping
@Slf4j
public class ItemController {

    @Autowired
    private CircuitBreakerFactory cbCircuitBreakerFactory;


    @Autowired
    @Qualifier("serviceFeignProducto")
    private ItemService itemService;

    @GetMapping("/listar")
    public List<Item> listar(){
        return itemService.findAll();
    }

    @GetMapping("/listar/{id}/cantidad/{cantidad}")
    public Item listarById(@PathVariable Long id, @PathVariable Integer cantidad)  {

        return cbCircuitBreakerFactory.create("items")
                .run(()->itemService.findById(id,cantidad), e -> metodoAlternativo(id,cantidad,e));
    }

    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver2/{id}/cantidad/{cantidad}")
    public Item listarById2(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemService.findById(id,cantidad);
    }

    public Item metodoAlternativo(Long id, Integer cantidad,Throwable e) {
        log.info("Error generated " + e);
        Producto producto = Producto.builder().id(id).nombre("camara")
                        .precio(500.00).build();
        return Item.builder().cantidad(cantidad).producto(producto).build();
    }

    //TIME LIMITER USE.
    @CircuitBreaker(name = "items", fallbackMethod = "metodoAlternativo2")
    @TimeLimiter(name = "items")
    @GetMapping("/ver3/{id}/cantidad/{cantidad}")
    public CompletableFuture <Item> listarById3(@PathVariable Long id, @PathVariable Integer cantidad){
        return CompletableFuture.supplyAsync(() ->  itemService.findById(id,cantidad));
    }

    public CompletableFuture <Item> metodoAlternativo2(Long id, Integer cantidad,Throwable e) {
        log.info("Error generated " + e);
        Producto producto = Producto.builder().id(id).nombre("camara")
                .precio(500.00).build();
        return CompletableFuture.supplyAsync(() -> Item.builder().cantidad(cantidad)
                .producto(producto).build()) ;
    }
}
