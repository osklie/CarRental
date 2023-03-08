package Exception;

import domain.dto.Model;

public class ModelAlreadyExistException extends EntityAlreadyExistException {

    public ModelAlreadyExistException() {
        super(Model.class);
    }
}