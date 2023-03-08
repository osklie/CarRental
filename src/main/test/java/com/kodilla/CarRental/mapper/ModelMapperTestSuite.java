package com.kodilla.CarRental.mapper;

import domain.dto.Mark;
import domain.dto.Model;
import domain.dto.ModelDto;
import enums.CarType;
import enums.FuelType;
import enums.GearBox;
import mapper.ModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ModelMapperTestSuite {

    @InjectMocks
    private ModelMapper modelMapper;

    private Model model;
    private ModelDto modelDto;
    private final List<Model> modelList = new ArrayList<>();
    private Mark mark;

    @BeforeEach
    void prepareData() {
        mark = new Mark(1L, "test name", new HashSet<>());
        //model = new Model(1L, mark, "name", 1800.00, CarType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, GearBox.AUTOMATIC, new HashSet<>());
        modelDto = new ModelDto(1L, mark, "name", 1800.00, CarType.SEDAN, 2012, "red", 5, 4, FuelType.DIESEL, GearBox.AUTOMATIC, new HashSet<>());
        modelList.add(model);
    }

    @Test
    void mapToModelTest() {
        //When
        Model expected = modelMapper.mapToModel(modelDto);
        //Then
        assertEquals(1L, expected.getModelId());
        assertEquals(mark, expected.getMarkDto());
        assertEquals("name", expected.getName());
        assertEquals(1800.00, expected.getEngineSize());
        assertEquals(CarType.SEDAN, expected.getBodyType());
        assertEquals(2012, expected.getProductionYear());
        assertEquals("red", expected.getColor());
        assertEquals(5, expected.getSeatsQuantity());
        assertEquals(4, expected.getDoorQuantity());
        assertEquals(FuelType.DIESEL, expected.getFuelType());
        assertEquals(GearBox.AUTOMATIC, expected.getTransmissionType());
    }

    @Test
    void mapToModelDtoTest() {
        //When
        ModelDto expected = modelMapper.mapToModelDto(model);
        //Then
        assertEquals(1L, expected.getModelId());
        assertEquals(mark, expected.getMark());
        assertEquals("name", expected.getName());
        assertEquals(1800.00, expected.getEngineSize());
        assertEquals(CarType.SEDAN, expected.getCarType());
        assertEquals(2012, expected.getProductionYear());
        assertEquals("red", expected.getColor());
        assertEquals(5, expected.getSeatsQuantity());
        assertEquals(4, expected.getDoorQuantity());
        assertEquals(FuelType.DIESEL, expected.getFuelType());
        assertEquals(GearBox.AUTOMATIC, expected.getGearbox());
    }

    @Test
    void mapToModelDtoListTest() {
        //When
        List<ModelDto> expected = modelMapper.mapToModelDtoList(modelList);
        //Then
        assertEquals(1, expected.size());
    }
}