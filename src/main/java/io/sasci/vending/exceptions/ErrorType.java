package io.sasci.vending.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    OUT_OF_STOCK_EXCEPTION("1001", "This product is not available now"),
    INSUFFICIENT_BALANCE_EXCEPTION("1002","Balance is insufficient to get this product"),
    UNKNOWN_PRODUCT_EXCEPTION("1003","There is no such a product"),
    INSUFFICIENT_REFUND_EXCEPTION("1004","Insufficient coins to refund");

    private String code;
    private String message;
}
