package controller;

import Service.UserDbService;
import domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserDbService userDbService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userDbService.getAllUsers();
    }

    @GetMapping(value = "{userId}")
    public UserDto getUser(@PathVariable long userId) {
        return userDbService.getUser(userId);
    }

    @DeleteMapping(value = "{userId}")
    public void deleteUser(@PathVariable long userId) {
        userDbService.deleteUser(userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto addUser(@RequestBody UserDto userDto){
        return userDbService.createUser(userDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userDbService.updateUser(userDto);
    }
}