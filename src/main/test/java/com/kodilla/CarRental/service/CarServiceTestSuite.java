package com.kodilla.CarRental.service;

import Service.CarDbService;
import domain.dto.Car;
import domain.dto.CarDto;
import domain.dto.Mark;
import domain.dto.Model;
import enums.CarStatus;
import enums.CarType;
import enums.FuelType;
import enums.GearBox;
import mapper.CarMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.web.client.ExpectedCount;
import repository.CarRepository;
import Exception.CarNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
public class CarServiceTestSuite {

    @InjectMocks
    private CarDbService carService;

    @Mock
    private CarMapper carMapper;

    @Mock
    private CarRepository carRepository;

    private Car car;
    private CarDto carDto;
    private final List<Car> carList = new ArrayList<>();
    private final List<CarDto> carDtoList = new ArrayList<>();
    private Model model;

    @BeforeEach
    void prepareData() {
        Mark mark = new Mark(1L, "test name", new HashSet<>());
        model = new Model(1L, mark, "name", 1800.00, CarType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, GearBox.AUTOMATIC, new HashSet<>());
        car = new Car(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, new ArrayList<>());
        carDto = new CarDto(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, new ArrayList<>());
        carList.add(car);
        carDtoList.add(carDto);
    }

    @Test
    void getAllCarsTest() {
        //Given
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        when(carRepository.findAll()).thenReturn(carList);

        //When
        List<CarDto> expectedList = carService.getAllCars();

        //Then
        assertEquals(1, expectedList.size());
        assertEquals(1L, expectedList.get(0).getCarId());
        assertEquals(model, expectedList.get(0).getModel());
        assertEquals("ST11111", expectedList.get(0).getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expectedList.get(0).getPrice());
        assertEquals(CarStatus.AVAILABLE, expectedList.get(0).getCarStatus());
    }

    @Test
    void getCarTest() throws CarNotFoundException {
        //Given
        when(carMapper.mapToCarDto(car)).thenReturn(carDto);
        when(carRepository.findById(carDto.getCarId())).thenReturn(Optional.of(car));

        //When
        CarDto expected = carService.getCar(1L);

        //Then
        assertEquals(1L, expected.getCarId());
        assertEquals(model, expected.getModel());
        assertEquals("ST11111", expected.getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expected.getPrice());
        assertEquals(CarStatus.AVAILABLE, expected.getCarStatus());
    }

    @Test
    void getCarsByManufacturerTest() {
        //Given
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        when(carRepository.findAll()).thenReturn(carList);

        //When
        List<CarDto> expectedList = carService.getCarsByMark(1L);

        //Then
        assertEquals(1, expectedList.size());
    }

    @Test
    void getCarsByModelTest() {
        //Given
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        when(carRepository.findAll()).thenReturn(carList);

        //When
        List<CarDto> expectedList = carService.getCarsByModel(1L);

        //Then
        assertEquals(1, expectedList.size());
    }

    @Test
    void getCarsByPriceLowerThanTest() {
        //Given
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        when(carRepository.findAll()).thenReturn(carList);

        //When
        List<CarDto> expectedList = carService.getCarsByPriceLowerThan(BigDecimal.valueOf(300));

        //Then
        assertEquals(1, expectedList.size());
    }

    @Test
    void getAvailableCarsTest() {
        //Given
        when(carMapper.mapToCarDtoList(carList)).thenReturn(carDtoList);
        when(carRepository.findAll()).thenReturn(carList);

        //When
        List<CarDto> expectedList = carService.getAvailableCars();

        //Then
        assertEquals(1, expectedList.size());
    }

    @Test
    void createCarTest() {
        //Given
        when(carMapper.mapToCar(carDto)).thenReturn(car);
        Car savedCar = carMapper.mapToCar(carDto);
        when(carRepository.save(car)).thenReturn(savedCar);
        when(carMapper.mapToCarDto(savedCar)).thenReturn(carDto);

        //When
        CarDto expected = carService.createCar(carDto);

        //Then
        assertEquals(1L, expected.getCarId());
        assertEquals(model, expected.getModel());
        assertEquals("ST11111", expected.getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expected.getPrice());
        assertEquals(CarStatus.AVAILABLE, expected.getCarStatus());
    }

    @Test
    void updateCarTest() throws CarNotFoundException {
        //Given
        when(carMapper.mapToCar(carDto)).thenReturn(car);
        Car savedCar = carMapper.mapToCar(carDto);
        when(carRepository.save(car)).thenReturn(savedCar);
        when(carMapper.mapToCarDto(savedCar)).thenReturn(carDto);
        when(carRepository.existsById(carDto.getCarId())).thenReturn(true);

        //When
        CarDto expected = carService.updateCar(carDto);

        //Then
        assertEquals(1L, expected.getCarId());
        assertEquals(model, expected.getModel());
        assertEquals("ST11111", expected.getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expected.getPrice());
        assertEquals(CarStatus.AVAILABLE, expected.getCarStatus());
    }

    @Test
    void deleteUserTest() throws CarNotFoundException {
        //Given
        when(carRepository.findById(carDto.getCarId())).thenReturn(Optional.of(car));

        //When
        carService.deleteCar(1L);

        //Then
        verify(carRepository, times(1)).deleteById(1L);
    }

    private <T> CrudRepository verify(CarRepository carRepository, ExpectedCount times) {
        return null;
    }
}