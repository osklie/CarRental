package scheduler;

import Service.EmailService;
import config.AdminConfig;
import domain.dto.Mail;
import domain.dto.Rental;
import domain.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repository.RentalRepository;
import repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Rental Application: Daily Report";
    private final EmailService emailService;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationToAdmin() {
        emailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "Yesterday " + getNewUsers() + (getNewUsers() ==1 ? " user was" : " users were") + " created and "
                                + getNewRentals() + (getNewRentals() ==1 ? " rental was made" : " rentals were made")
                )
        );
    }

    public long getNewRentals() {
        List<Rental> yesterdayRentals = rentalRepository.findAll().stream()
                .filter(r -> r.getRentDate().equals(LocalDate.now().minusDays(1)))
                .collect(Collectors.toList());
        return yesterdayRentals.size();
    }

    public long getNewUsers() {
        List<User> yesterdayNewUsers = userRepository.findAll().stream()
                .filter(r -> r.getSignupDate().equals(LocalDate.now().minusDays(1)))
                .collect(Collectors.toList());
        return yesterdayNewUsers.size();
    }
}