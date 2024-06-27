package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.exception;

public class FlowerServiceException extends RuntimeException {
    public FlowerServiceException(String message) {
        super(message);
    }

    public FlowerServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
