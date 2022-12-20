package uao.edu.co.sinapsis_app.exceptions;

public class StorageFileNotFoundException extends RuntimeException{
    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
