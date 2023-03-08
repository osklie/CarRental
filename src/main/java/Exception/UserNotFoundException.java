package Exception;

import domain.dto.User;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(long id) {
        super(User.class, id);
    }
}
