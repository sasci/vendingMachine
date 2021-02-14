package io.sasci.vending.repo;


import io.sasci.vending.exceptions.ErrorType;
import io.sasci.vending.exceptions.VendingMachineException;
import io.sasci.vending.model.Coin;
import io.sasci.vending.model.Product;

import java.util.HashMap;
import java.util.Map;


public class VendingMachineInventory {
    Map<Product,Integer> productInventory=new HashMap<>();
    Map<Coin,Integer> coinInventory=new HashMap<>();

    public static VendingMachineInventory createInventory(){
        final VendingMachineInventory vendingMachineInventory = new VendingMachineInventory();
        vendingMachineInventory.addProduct(Product.COKE, 2);
        vendingMachineInventory.addProduct(Product.PEPSI, 3);
        vendingMachineInventory.addProduct(Product.SODA, 0);
        return vendingMachineInventory;
    }

    public Integer getProductQuantity(Product product){
        if (!productInventory.containsKey(product)){
            throw new VendingMachineException(ErrorType.UNKNOWN_PRODUCT_EXCEPTION);
        }
        return productInventory.get(product);
    }

    public void addProduct(Product product, Integer quantity){
        if(quantity==null || quantity<0) return;
        if (productInventory.containsKey(product.getName())){
            final Integer currentQuantity = productInventory.get(product);
            productInventory.put(product, currentQuantity +quantity);
        }else{
            productInventory.put(product,quantity);
        }
    }

    public void addCoin(Coin coin, Integer quantity){
        if(quantity==null || quantity<0) return;
        if (coinInventory.containsKey(coin.getName())){
            final Integer currentQuantity = productInventory.get(coin);
            coinInventory.put(coin, currentQuantity +quantity);
        }else{
            coinInventory.put(coin,quantity);
        }
    }

    public boolean removeProduct(Product product){
        Integer count = productInventory.get(product);
        if (count == null || count==0) {
            throw new VendingMachineException(ErrorType.OUT_OF_STOCK_EXCEPTION);
        }
        productInventory.put(product,--count);
        return true;
    }
}
