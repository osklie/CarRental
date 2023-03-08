package com.kodilla.CarRental.mapper;

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
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CarMapperTestSuite {

    @InjectMocks
    private CarMapper carMapper;

    private Car car;
    private CarDto carDto;
    private final List<Car> carList = new ArrayList<>();
    private Model model;

    @BeforeEach
    void prepareData() {
        Mark mark = new Mark(1L, "test name", new HashSet<>());
        //model = new Model(1L, mark, "name", 1800.00, CarType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, GearBox.AUTOMATIC, new HashSet<>());
        car = new Car(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, new ArrayList<>());
        carDto = new CarDto(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, new ArrayList<>());
        carList.add(car);
    }

    @Test
    void mapToCarTest() {
        //When
        Car expected = carMapper.mapToCar(carDto);
        //Then
        assertEquals(1L, expected.getCarId());
        assertEquals(model, expected.getModel());
        assertEquals("ST11111", expected.getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expected.getPrice());
        assertEquals(CarStatus.AVAILABLE, expected.getCarStatus());
    }

    @Test
    void mapToCarDtoTest() {
        //When
        CarDto expected = carMapper.mapToCarDto(car);
        //Then
        assertEquals(1L, expected.getCarId());
        assertEquals(model, expected.getModel());
        assertEquals("ST11111", expected.getLicenseNumber());
        assertEquals(BigDecimal.valueOf(150), expected.getPrice());
        assertEquals(CarStatus.AVAILABLE, expected.getCarStatus());
    }

    @Test
    void mapToCarDtoListTest() {
        //When
        List<CarDto> expected = carMapper.mapToCarDtoList(carList);
        //Then
        assertEquals(1, expected.size());
    }
}