package Exception;

import domain.dto.Model;

public class ModelNotFoundException extends EntityNotFoundException {

    public ModelNotFoundException(long id) {
        super(Model.class, id);
    }
}