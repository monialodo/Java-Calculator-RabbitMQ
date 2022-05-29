package service.errors;

public class DivideByZeroException extends Exception {

    public DivideByZeroException (String errorMessage) {
        super(errorMessage);
    }

}
