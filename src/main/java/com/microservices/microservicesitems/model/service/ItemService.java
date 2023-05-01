package com.microservices.microservicesitems.model.service;

import com.microservices.microservicesitems.model.Item;

import java.util.List;

public interface ItemService {

    public List<Item> findAll();
    public Item findById(Long id,Integer cantidad);


}
