package Exception;

import domain.dto.Car;

import javax.persistence.EntityNotFoundException;

public class CarNotFoundException extends EntityNotFoundException {

    public CarNotFoundException(long id) {
      //  super(Car.class, id);
    }
}