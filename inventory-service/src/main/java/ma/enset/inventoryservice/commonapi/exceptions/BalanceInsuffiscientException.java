package ma.enset.inventoryservice.commonapi.exceptions;

public class BalanceInsuffiscientException extends RuntimeException {
    public BalanceInsuffiscientException(String message) {
        super(message);
    }
}
