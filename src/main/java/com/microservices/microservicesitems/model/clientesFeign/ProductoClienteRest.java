package com.microservices.microservicesitems.model.clientesFeign;

import com.microservices.microservicesitems.model.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="servicio-producto",url = "http://localhost:8001")
public interface ProductoClienteRest {

    @GetMapping("/listar")
    public List<Producto> listarProductos();

    @GetMapping("/ver/{id}")
    public Producto detalleProducto(@PathVariable long id);

    @PostMapping("/agregar")
    public Producto addProducto(@RequestBody Producto producto);

}
