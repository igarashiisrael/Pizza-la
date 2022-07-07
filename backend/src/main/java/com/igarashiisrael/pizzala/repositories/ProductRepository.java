package com.igarashiisrael.pizzala.repositories;

import com.igarashiisrael.pizzala.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long>{

}
