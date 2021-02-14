package io.sasci.vending.exceptions;

import lombok.Getter;

@Getter
public class VendingMachineException extends RuntimeException {
    private ErrorType errorType;

    public VendingMachineException(ErrorType errorType){
        super(errorType.getMessage());
    }
}
