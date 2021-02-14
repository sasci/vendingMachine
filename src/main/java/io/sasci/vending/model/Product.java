package io.sasci.vending.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Product {
    COKE("Coke",25),
    PEPSI("Pepsi",35),
    SODA("Soda",45);

    private String name;
    private Integer  price;
}