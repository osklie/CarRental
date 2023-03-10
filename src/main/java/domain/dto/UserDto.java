package domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private long userId;
    private String firstName;
    private String lastName;
    private long pesel;
    private String address;
    private String mail;
    private String password;
    private String creditCardNo;
    private BigDecimal toPay;
    private LocalDate signupDate;

    @JsonIgnore
    private Set<Rental> rentals;
}