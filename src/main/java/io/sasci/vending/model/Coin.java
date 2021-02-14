package io.sasci.vending.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Coin {
    PENNY("penny",1)
    ,NICKEL("nickel",5)
    ,DIME("dime",10)
    ,QUARTER("quarter",25);

    private String name;
    private Integer value;

    public static List<Coin> getBigToSmall(){
        List<Coin> refundableList=new ArrayList<>();
        refundableList.add(Coin.QUARTER);
        refundableList.add(Coin.DIME);
        refundableList.add(Coin.NICKEL);
        refundableList.add(Coin.PENNY);
        return refundableList;
    }
}
