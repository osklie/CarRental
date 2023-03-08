package Exception;

import domain.dto.MarkDto;

public class MarkNotFoundException extends EntityNotFoundException {

    public MarkNotFoundException(long id) {
        super(MarkDto.class, id);
    }
}