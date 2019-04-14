package com.ps.project.warehouse.Repository;

import com.ps.project.warehouse.domain.Product;
import com.ps.project.warehouse.domain.ProductType;
import com.ps.project.warehouse.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Override
    Page<Product> findAll(Pageable pageable);
    List<Product> getAllByWarehouse(Warehouse warehouse);

    Product findByName(String name);

    Product findByNameAndWarehouse(String name, Warehouse warehouse);
    List<Product> findByProductType(ProductType productType);
}
