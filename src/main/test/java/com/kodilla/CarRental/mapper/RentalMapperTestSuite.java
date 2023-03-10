package com.kodilla.CarRental.mapper;

import domain.dto.*;
import enums.*;
import mapper.RentalMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RentalMapperTestSuite {

    @InjectMocks
    private RentalMapper rentalMapper;

    private Rental rental;
    private RentalDto rentalDto;
    private final List<Rental> rentalList = new ArrayList<>();
    private Car car;
    private User user;

    @BeforeEach
    void prepareData() {
        Mark mark = new Mark(1L, "test name", new HashSet<>());
       // Model model = new Model(1L, mark, "name", 1800.00, CarType.SEDAN, 2012, "red",
          //      5, 4, FuelType.DIESEL, GearBox.AUTOMATIC, new HashSet<>());
        //car = new Car(1L, model, "ST11111", BigDecimal.valueOf(150), CarStatus.AVAILABLE, new ArrayList<>());
        user = new User(1L, "name", "lastname", 940930123212L, "address", "mail@mail.com",
                "password", "123456789", BigDecimal.ZERO, LocalDate.of(2022,9,22), new HashSet<>());

       // rental = new Rental(1L, LocalDate.of(2022,10,10), LocalDate.of(2022,10,11), Currency.EUR,
               // 1.5, BigDecimal.valueOf(150), BigDecimal.valueOf(150), RentalStatus.RETURNED, LocalDate.of(2022,10,11), user, car);
      //  rentalDto = new RentalDto(1L, LocalDate.of(2022,10,10), LocalDate.of(2022,10,11), Currency.EUR,
          //      1.5, BigDecimal.valueOf(150), BigDecimal.valueOf(150), RentalStatus.RETURNED, LocalDate.of(2022,10,11), user, car);
        rentalList.add(rental);
    }

    @Test
    void mapToRentalTest() {
        //When
        Rental expected = rentalMapper.mapToRental(rentalDto);
        //Then
        assertEquals(1L, expected.getRentalId());
        assertEquals(LocalDate.of(2022,10,10), expected.getRentDate());
        assertEquals(LocalDate.of(2022,10,11), expected.getReturnDate());
        assertEquals(Currency.EUR, expected.getCurrency());
        assertEquals(1.5, expected.getPriceRate());
        assertEquals(BigDecimal.valueOf(150), expected.getTotalValue());
        assertEquals(BigDecimal.valueOf(150), expected.getLeftToPay());
        assertEquals(RentalStatus.RETURNED, expected.getRentalStatus());
        assertEquals(LocalDate.of(2022,10,11), expected.getPaymentDate());
        assertEquals(user, expected.getUser());
        assertEquals(car, expected.getCar());
    }

    @Test
    void mapToRentalDtoTest() {
        //When
        RentalDto expected = rentalMapper.mapToRentalDto(rental);
        //Then
        assertEquals(1L, expected.getRentalId());
        assertEquals(LocalDate.of(2022,10,10), expected.getRentDate());
        assertEquals(LocalDate.of(2022,10,11), expected.getReturnDate());
        assertEquals(Currency.EUR, expected.getCurrency());
        assertEquals(1.5, expected.getPriceRate());
        assertEquals(BigDecimal.valueOf(150), expected.getTotalValue());
        assertEquals(BigDecimal.valueOf(150), expected.getLeftToPay());
        assertEquals(RentalStatus.RETURNED, expected.getRentalStatus());
        assertEquals(LocalDate.of(2022,10,11), expected.getPaymentDate());
        assertEquals(user, expected.getUser());
        assertEquals(car, expected.getCar());
    }

    @Test
    void mapToRentalDtoListTest() {
        //When
        List<RentalDto> expected = rentalMapper.mapToRentalDtoList(rentalList);
        //Then
        assertEquals(1, expected.size());
    }
}