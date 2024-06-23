package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.exception;

public class BranchServiceException extends RuntimeException {
    public BranchServiceException(String message) {
        super(message);
    }

    public BranchServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
