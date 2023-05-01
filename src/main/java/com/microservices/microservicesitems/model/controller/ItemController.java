package com.microservices.microservicesitems.model.controller;

import com.microservices.microservicesitems.model.Item;
import com.microservices.microservicesitems.model.Producto;
import com.microservices.microservicesitems.model.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ItemController {

    @Autowired
    @Qualifier("serviceFeignProducto")
    private ItemService itemService;

    @GetMapping("/listar")
    public List<Item> listar(){
        return itemService.findAll();
    }

    @GetMapping("/listar/{id}/cantidad/{cantidad}")
    public Item listarById(@PathVariable Long id, @PathVariable Integer cantidad){
        return itemService.findById(id,cantidad);
    }



}
