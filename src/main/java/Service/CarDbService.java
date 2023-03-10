package Service;

import domain.dto.Car;
import Exception.CarNotFoundException;
import Exception.MarkNotFoundException;
import Exception.CarAlreadyExistException;
import domain.dto.CarDto;
import enums.CarStatus;
import lombok.RequiredArgsConstructor;
import mapper.CarMapper;
import org.springframework.stereotype.Service;
import repository.CarRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarDbService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return carMapper.mapToCarDtoList(cars);
    }

    public CarDto getCar(final long carId) throws CarNotFoundException {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        return carMapper.mapToCarDto(car);
    }

    public List<CarDto> getCarsByMark(final long markId) throws MarkNotFoundException {
        List<Car> cars = carRepository.findAll().stream()
                .filter(c -> c.getModel().getMark().getMarkId() == markId)
                .collect(Collectors.toList());
        return carMapper.mapToCarDtoList(cars);
    }

    public List<CarDto> getCarsByModel(final long modelId) throws MarkNotFoundException {
        List<Car> cars = carRepository.findAll().stream()
                .filter(c -> c.getModel().getModelId() == modelId)
                .collect(Collectors.toList());
        return carMapper.mapToCarDtoList(cars);
    }

    public List<CarDto> getCarsByPriceLowerThan(final BigDecimal price) {
        List<Car> cars = carRepository.findAll().stream()
                .filter(c -> c.getPrice().compareTo(price) <= 0)
                .collect(Collectors.toList());
        return carMapper.mapToCarDtoList(cars);
    }

    public List<CarDto> getAvailableCars() {
        List<Car> cars = carRepository.findAll().stream()
                .filter(c -> c.getCarStatus().equals(CarStatus.AVAILABLE))
                .collect(Collectors.toList());
        return carMapper.mapToCarDtoList(cars);
    }

    @Transactional
    public CarDto createCar(final CarDto carDto) throws CarAlreadyExistException {

        List<String> licenseNumbersList = getAllCars().stream()
                .map(CarDto::getLicenseNumber)
                .collect(Collectors.toList());

        if (!licenseNumbersList.contains(carDto.getLicenseNumber())) {
            Car car = carMapper.mapToCar(carDto);
            Car savedCar = carRepository.save(car);
            return carMapper.mapToCarDto(savedCar);
        } else {
            throw new CarAlreadyExistException();
        }
    }

    @Transactional
    public CarDto updateCar(final CarDto carDto) throws CarNotFoundException {

        if (!carRepository.existsById(carDto.getCarId())) {
            throw new CarNotFoundException(carDto.getCarId());
        } else {
            Car car = carMapper.mapToCar(carDto);
            Car savedCar = carRepository.save(car);
            return carMapper.mapToCarDto(savedCar);
        }
    }

    @Transactional
    public void deleteCar(final long carId) throws CarNotFoundException {
        Optional<Car> car = carRepository.findById(carId);

        if (car.isPresent()) {
            carRepository.deleteById(carId);
        } else {
            throw new CarNotFoundException(carId);
        }
    }
}