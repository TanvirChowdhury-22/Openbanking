package biz.buynow.bank.schedulejobs;

public class TransactionMaxLimitException extends Exception {
    private static final long serialVersionUID = 1L;

    public TransactionMaxLimitException(String errorMessage) {
        super(errorMessage);
    }
}