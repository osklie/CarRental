package Exception;

import domain.dto.Rental;

public class RentalNotFoundException extends EntityNotFoundException {

    public RentalNotFoundException(long id) {
        super(Rental.class, id);
    }
}