package com.ps.project.warehouse.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 3)
    private String name;

    private int amount = 0;

    @Basic
    @Size(min = 5)
    @NotNull
    private String description;

    @NotNull
    private String code;

    @NotNull
    @ManyToOne
    @JoinColumn
    ProductType productType;

    @Enumerated(EnumType.STRING)
    private Warehouse warehouse;

    public void setWarehouse (String warehouse) {
        this.warehouse = Warehouse.valueOf(warehouse.toUpperCase()) == null ? Warehouse.WAREHOUSE_1 : Warehouse.valueOf(warehouse.toUpperCase());
    }

    public void setWarehouse (Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}


