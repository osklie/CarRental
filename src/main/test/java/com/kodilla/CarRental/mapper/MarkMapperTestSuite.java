package com.kodilla.CarRental.mapper;

import domain.dto.Mark;
import domain.dto.MarkDto;
import mapper.MarkMapper;
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
public class MarkMapperTestSuite {

    @InjectMocks
    private MarkMapper markMapper;

    private Mark mark;
    private MarkDto markDto;
    private final List<Mark> markList = new ArrayList<>();

    @BeforeEach
    void prepareData() {
        mark = new Mark(1L, "test name", new HashSet<>());
        markDto = new MarkDto(1L, "test name", new HashSet<>());
        markList.add(mark);
    }

    @Test
    void mapToManufacturerTest() {
        //When
        Mark expected = markMapper.mapToMark(markDto);
        //Then
        assertEquals(1L, expected.getMarkId());
        assertEquals("test name", expected.getName());
    }

    @Test
    void mapToManufacturerDtoTest() {
        //When
        MarkDto expected = markMapper.mapToMarkDto(mark);
        //Then
        assertEquals(1L, expected.getMarkId());
        assertEquals("test name", expected.getName());
    }

    @Test
    void mapToManufacturerDtoListTest() {
        //When
        List<MarkDto> expected = markMapper.mapToMarkDtoList(markList);
        //Then
        assertEquals(1, expected.size());
    }
}