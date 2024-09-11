package com.springcourse.services;

import com.springcourse.models.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService{

    @Value("${misurls.database}")
    private String databaseUrl;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public void saveOrder(List<Producto> productos){
        System.out.println("Guardando en la base de datos: " + databaseUrl);
        productos.forEach(producto -> logger.debug("el nombre del producto: {}", producto.nombre));
    }
}
