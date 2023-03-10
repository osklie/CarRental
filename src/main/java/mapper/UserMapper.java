package mapper;

import domain.dto.User;
import domain.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPesel(),
                userDto.getAddress(),
                userDto.getMail(),
                userDto.getPassword(),
                userDto.getCreditCardNo(),
                userDto.getToPay(),
                userDto.getSignupDate(),
                userDto.getRentals()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPesel(),
                user.getAddress(),
                user.getMail(),
                user.getPassword(),
                user.getCreditCardNo(),
                user.getToPay(),
                user.getSignupDate(),
                user.getRentals()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}