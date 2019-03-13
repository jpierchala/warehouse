package com.ps.project.warehouse.Repository;

import com.ps.project.warehouse.domain.Product;
import com.ps.project.warehouse.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Override
    List<Product> findAll();

    List<Product> getAllByWarehouse(Warehouse warehouse);

    Product findByName(String name);

    Product findByNameAndWarehouse(String name, Warehouse warehouse);
}
