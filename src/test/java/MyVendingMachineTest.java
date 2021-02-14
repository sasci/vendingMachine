import io.sasci.vending.MyVending;
import io.sasci.vending.exceptions.VendingMachineException;
import io.sasci.vending.model.Coin;
import io.sasci.vending.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyVendingMachineTest {
    private MyVending myVending;

    @BeforeEach
    public void setup(){
        myVending=new MyVending();
    }

    @Test
    @DisplayName("Accept 1,5,10,25 Cents")
    public void acceptCoinTest(){
        myVending.acceptCoin(Coin.DIME);
        myVending.acceptCoin(Coin.valueOf("PENNY"));
        assertEquals(myVending.total,11);
    }

    @Test
    @DisplayName("Choosing product with insufficient balance will cause an exception")
    public void insufficientBalanceExceptionTest(){
        myVending.acceptCoin(Coin.DIME);
        myVending.acceptCoin(Coin.valueOf("PENNY"));
        assertThrows(VendingMachineException.class,()->{myVending.chooseProduct(Product.COKE);});
    }

    @Test
    @DisplayName("Verify out of stock exception message with assertj library")
    public void outOfStockExceptionTestFail(){
        myVending.acceptCoin(Coin.DIME);
        myVending.acceptCoin(Coin.DIME);
        myVending.acceptCoin(Coin.QUARTER);
        final Throwable outOfStock = catchThrowable(() -> {
                    myVending.chooseProduct(Product.SODA);
                }
        );
        assertThat(outOfStock.getMessage()).isEqualTo("This product is not available now");
    }

    @Test
    @DisplayName("Out of Stock Product will throw exception")
    public void outOfStockExceptionTest(){
        myVending.acceptCoin(Coin.DIME);
        myVending.acceptCoin(Coin.DIME);
        myVending.acceptCoin(Coin.QUARTER);
        assertThrows(VendingMachineException.class,()->{myVending.chooseProduct(Product.SODA);});
    }

    @Test
    @DisplayName("Select product and Check Inventory")
    public void selectProductTest(){
        myVending.acceptCoin(Coin.QUARTER);
        myVending.acceptCoin(Coin.DIME);
        myVending.acceptCoin(Coin.valueOf("PENNY"));
        Product coke=Product.COKE;
        final Integer cokeQuantity = myVending.inventory.getProductQuantity(coke);
        myVending.chooseProduct(coke);
        assertEquals(myVending.inventory.getProductQuantity(coke),cokeQuantity-1);
    }

    @Test
    @DisplayName("Select unavailable product and get exception")
    public void selectUnavailableProductTest(){
        myVending.acceptCoin(Coin.DIME);
        myVending.acceptCoin(Coin.DIME);
        myVending.acceptCoin(Coin.NICKEL);
        assertThrows(IllegalArgumentException.class,()->{myVending.chooseProduct(Product.valueOf("SPRITE"));});
    }

    @Test
    @DisplayName("Select Product and Check refunded Coins")
    public void refundTest(){
        myVending.acceptCoin(Coin.QUARTER);
        myVending.acceptCoin(Coin.DIME);
        myVending.acceptCoin(Coin.valueOf("PENNY"));
        myVending.chooseProduct(Product.COKE);
        int afterProductSelection=myVending.total;
        final List<Coin> refundedCoins = myVending.refundOverPayment();
        final int refundedSum = myVending.sumOfRefund(refundedCoins);
        assertEquals(afterProductSelection,refundedSum);
        int afterRefund=myVending.total;
        assertEquals(afterRefund,0);
    }

    @Test
    @DisplayName("Insert coins then cancel process and get coins back")
    public void cancelProcessTest(){
        myVending.acceptCoin(Coin.QUARTER);
        myVending.acceptCoin(Coin.DIME);
        myVending.acceptCoin(Coin.valueOf("PENNY"));
        int afterInsertCoin=myVending.total;
        assertEquals(afterInsertCoin,36);
        final Integer refunded = myVending.cancelProcess();
        assertEquals(refunded,afterInsertCoin);
        assertEquals(myVending.total,0);
    }
    @Test
    @DisplayName("Reset after inserting coins and total should be 0")
    public void resetTest(){
        myVending.acceptCoin(Coin.QUARTER);
        myVending.acceptCoin(Coin.DIME);
        final Integer beforeResetTotal = myVending.total;
        assertThat(beforeResetTotal).isEqualTo(35);
        myVending.reset();
        final Integer afterResetTotal = myVending.total;
        assertThat(afterResetTotal).isEqualTo(0);
    }
}
