package com.springcourse.myBeans;

import com.springcourse.models.Producto;
import com.springcourse.services.IOrderService;
import com.springcourse.services.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MisPrimerosBeans {

    @Bean
    public MiBean crearMiBean(){
        return new MiBean();
    }

    @Bean
    public IOrderService instanciarOrderService(){
        boolean esProduccion = false;
        if(esProduccion){
            return new OrderService();
        }{
            return new IOrderService() {
                @Override
                public void saveOrder(List<Producto> productos) {
                    System.out.println("Guardando en base de datos dummy (local)");
                }
            };
        }
    }

}
