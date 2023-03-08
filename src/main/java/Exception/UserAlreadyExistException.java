package Exception;

import domain.dto.User;

public class UserAlreadyExistException extends EntityAlreadyExistException {

    public UserAlreadyExistException() {
        super(User.class);
    }
}
