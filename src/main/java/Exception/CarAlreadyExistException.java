package Exception;

import domain.dto.Car;

public class CarAlreadyExistException extends EntityAlreadyExistException {

    public CarAlreadyExistException() {
        super(Car.class);
    }
}