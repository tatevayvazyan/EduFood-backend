package am.mse.eduFood.rest;

import am.mse.eduFood.domain.User;
import am.mse.eduFood.dto.UserDto;
import am.mse.eduFood.service.UserService;
import javassist.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserRestApi {

    private final UserService userService;

    public UserRestApi(UserService userService) {

        this.userService = userService;
    }



    @GetMapping("/all")
    List<UserDto> all() {
        return userService.getAllUsers();
    }


    @PostMapping("/")
    void newUser(@RequestBody
        User newUser) {
         userService.addUser(newUser);
    }

    @GetMapping("/{id}")
    UserDto one(@PathVariable
        Long id) throws NotFoundException {

        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    UserDto oneByUsername(@PathVariable
        String username) {

        return userService.getUserByUsername(username);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
