package Exception;

import domain.dto.Mark;

public class MarkAlreadyExistException extends EntityAlreadyExistException {

    public MarkAlreadyExistException() {
        super(Mark.class);
    }
}