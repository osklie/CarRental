package domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "rentals")
public class Rental{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private long rentalId;

    private LocalDate rentDate;
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Double priceRate;
    private BigDecimal totalValue;
    private BigDecimal leftToPay;

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car")
    private Car car;
}