package com.ps.project.warehouse.config.bootstrap;

import com.ps.project.warehouse.Repository.ProductRepository;
import com.ps.project.warehouse.Repository.ProductTypeRepository;
import com.ps.project.warehouse.domain.Product;
import com.ps.project.warehouse.domain.ProductType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BootstrapProductsAndProductTypes implements InitializingBean {

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        bootstrapProductTypes();
        bootstrapProducts();
    }

    private void bootstrapProductTypes(){
        ProductType productType = null;
        if(productTypeRepository.findAll().isEmpty()){
            log.info("Bootstrapping product types...");
            productType = new ProductType();
            productType.setName("Śruby Sześciokątne");
            productTypeRepository.save(productType);

            productType = new ProductType();
            productType.setName("Śruby z gniazdem sześciokątnym");
            productTypeRepository.save(productType);

            productType = new ProductType();
            productType.setName("Nakrętki");
            productTypeRepository.save(productType);

            productType = new ProductType();
            productType.setName("Wkręty");
            productTypeRepository.save(productType);
            log.info("Bootstrapping done");

        }
    }

    private void bootstrapProducts(){
        if(productTypeRepository.findAll().isEmpty()){
            log.warn("Could not bootstrap products. No product types specified.");
            return;
        }else if (!productRepository.findAll().isEmpty()){
            log.warn("There are products in database");
            return;
        }else {

            log.info("Bootstrapping products...");
            //śruby sześciokątny
            ProductType productType = productTypeRepository.findByName("Śruby Sześciokątne");
            Product product = new Product();
            product.setName("Śruba sześciokątna z gwintem na całej długości");
            product.setDescription("St., Ocynk, DACROM, Gorący ocynk, A2 , A4 , Ms");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_1");
            product.setProductType(productType);
            product.setCode("DIN 933");
            productRepository.save(product);

            product = new Product();
            product.setName("Śruba sześciokątna z gwintem na całej długości");
            product.setDescription("St., Ocynk, DACROM, Gorący ocynk, A2 , A4 , Ms");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_2");
            product.setProductType(productType);
            product.setCode("DIN 933");
            productRepository.save(product);

            product = new Product();
            product.setName("Śruba sześciokątna z gwintem na części długości");
            product.setDescription("St., Ocynk, DACROM, Gorący ocynk, A2 , A4 , Ms");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_1");
            product.setProductType(productType);
            product.setCode("DIN 931");
            productRepository.save(product);

            product = new Product();
            product.setName("Śruba sześciokątna z gwintem na części długości");
            product.setDescription("St., Ocynk, DACROM, Gorący ocynk, A2 , A4 , Ms");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_2");
            product.setProductType(productType);
            product.setCode("DIN 931");
            productRepository.save(product);

            product = new Product();
            product.setName("Śruba z łbem stożkowym z podsadzeniem");
            product.setDescription("Ocynk, , A2 , A4 , Ms");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_1");
            product.setProductType(productType);
            product.setCode("DIN 608");
            productRepository.save(product);

            product = new Product();
            product.setName("Śruba z łbem stożkowym z podsadzeniem");
            product.setDescription("Ocynk, , A2 , A4 , Ms");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_2");
            product.setProductType(productType);
            product.setCode("DIN 608");
            productRepository.save(product);

            product = new Product();
            product.setName("Śruba z łbem sześciokątnym dociskowa");
            product.setDescription("St., Ocynk.");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_1");
            product.setProductType(productType);
            product.setCode("DIN 611");
            productRepository.save(product);

            //śruby z gniazdem sześciokątnym

            productType = productTypeRepository.findByName("Śruby z gniazdem sześciokątnym");
            product = new Product();
            product.setName("Śruba imbusowa");
            product.setDescription("St., Ocynk, DACROM, Gorący");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_1");
            product.setProductType(productType);
            product.setCode("DIN 912");
            productRepository.save(product);

            product = new Product();
            product.setName("Śruba imbusowa");
            product.setDescription("St., Ocynk, DACROM, Gorący");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_2");
            product.setProductType(productType);
            product.setCode("DIN 912");
            productRepository.save(product);

            product = new Product();
            product.setName("Śruba imbusowa pasowana");
            product.setDescription("St. A2");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_2");
            product.setProductType(productType);
            product.setCode("DIN 7984/6912");
            productRepository.save(product);

            //nakrętki

            productType = productTypeRepository.findByName("Nakrętki");
            product = new Product();
            product.setName("Nakrętka sześciokątna");
            product.setDescription("St, Ocynk, Gorący ocynk, Dacromet, A2, A4, Mosiądz, Miedz, Aluminium, Plastik.");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_1");
            product.setProductType(productType);
            product.setCode("DIN 439");
            productRepository.save(product);

            product = new Product();
            product.setName("Nakrętka sześciokątna");
            product.setDescription("St, Ocynk, Gorący ocynk, Dacromet, A2, A4, Mosiądz, Miedz, Aluminium, Plastik.");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_2");
            product.setProductType(productType);
            product.setCode("DIN 439");
            productRepository.save(product);

            product = new Product();
            product.setName("Nakrętka kwadratowa");
            product.setDescription("Ocynk, A2, A4");
            product.setAmount(100);
            product.setWarehouse("WAREHOUSE_1");
            product.setProductType(productType);
            product.setCode("DIN 436");
            productRepository.save(product);
            log.info("Bootstrapping products done.");
        }
    }
}
