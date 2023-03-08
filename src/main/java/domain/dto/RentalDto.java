package domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalDto {

    private long rentalId;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private Currency currency;
    private Double priceRate;
    private BigDecimal totalValue;
    private BigDecimal leftToPay;
    private RentalStatus rentalStatus;
    private LocalDate paymentDate;
    private User user;
    private Car car;
}