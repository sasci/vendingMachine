package io.sasci.vending;

import io.sasci.vending.exceptions.ErrorType;
import io.sasci.vending.exceptions.VendingMachineException;
import io.sasci.vending.model.Coin;
import io.sasci.vending.model.Product;
import io.sasci.vending.repo.VendingMachineInventory;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MyVending {
    public VendingMachineInventory inventory=VendingMachineInventory.createInventory();
    public Integer total=0;
    EnumSet<Coin> coins=EnumSet.allOf(Coin.class);
    private List<Coin> acceptedCoins=new ArrayList<>();



    public Integer acceptCoin(Coin coin){
        if(coins.contains(coin)){
            total+=coin.getValue();
            acceptedCoins.add(coin);
        }
        return total;
    }

    public String chooseProduct(Product product){
        if(total < product.getPrice()) {
            throw new VendingMachineException(ErrorType.INSUFFICIENT_BALANCE_EXCEPTION);
        }
        inventory.removeProduct(product);
        total-= product.getPrice();
        return product.getName();
    }

    public List<Coin> refundOverPayment(){
        List<Coin> refundCoins=new ArrayList<>();
        while(total>0){
            for (Coin c:Coin.getBigToSmall()) {
                int numCoins=total/c.getValue();
                for (int i = 0; i < numCoins; i++) {
                    refundCoins.add(c);
                }
                total%=c.getValue();
            }
        }
        if(total!=0){
            total=0;
            throw new VendingMachineException(ErrorType.INSUFFICIENT_REFUND_EXCEPTION);
        }
        return refundCoins;
    }

    public int sumOfRefund(List<Coin> refundCoins) {
        return refundCoins.stream().mapToInt(Coin::getValue).sum();
    }

    public Integer cancelProcess(){
        Integer refund=total;
        acceptedCoins=new ArrayList<>();
        total=0;
        return refund;
    }

    public void reset(){
        refundOverPayment();
        acceptedCoins=new ArrayList<>();
        total=0;
    }

}
