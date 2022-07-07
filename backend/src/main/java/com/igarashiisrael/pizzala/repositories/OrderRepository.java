package com.igarashiisrael.pizzala.repositories;

import com.igarashiisrael.pizzala.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order, Long> {
}
