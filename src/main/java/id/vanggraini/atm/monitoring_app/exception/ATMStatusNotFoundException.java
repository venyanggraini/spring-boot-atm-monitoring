package id.vanggraini.atm.monitoring_app.exception;

public class ATMStatusNotFoundException extends RuntimeException {
    public ATMStatusNotFoundException(String atmId) {
        super(String.format("ATM Status not found with id %s", atmId));
    }
    
}
