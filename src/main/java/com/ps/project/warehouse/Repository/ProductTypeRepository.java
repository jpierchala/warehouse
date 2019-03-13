package com.ps.project.warehouse.Repository;

import com.ps.project.warehouse.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    @Override
    List<ProductType> findAll();
    ProductType findByName(String name);
}
