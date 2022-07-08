package com.igarashiisrael.pizzala.services;

import com.igarashiisrael.pizzala.dto.OrderDTO;
import com.igarashiisrael.pizzala.dto.ProductDTO;
import com.igarashiisrael.pizzala.entities.Order;
import com.igarashiisrael.pizzala.entities.OrderStatus;
import com.igarashiisrael.pizzala.entities.Product;
import com.igarashiisrael.pizzala.repositories.OrderRepository;
import com.igarashiisrael.pizzala.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
        List<Order> list = repository.findOrderWithProducts();
        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
        for(ProductDTO p : dto.getProducts()){
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }
        order = repository.save(order);
        return new OrderDTO(order);
    }

}
