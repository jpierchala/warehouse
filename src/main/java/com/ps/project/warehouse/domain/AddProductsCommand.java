package com.ps.project.warehouse.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Setter
@Getter
public class AddProductsCommand {

    private int amount;

    private Long sourceProductId;

    @NotNull
    private long productId;

}
