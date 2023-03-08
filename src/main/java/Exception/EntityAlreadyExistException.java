package Exception;

public class EntityAlreadyExistException extends RuntimeException{

    public EntityAlreadyExistException(Class className) {
        super(className.getSimpleName() + " already exist!");
    }
}
