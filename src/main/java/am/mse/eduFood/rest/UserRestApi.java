package am.mse.eduFood.rest;

import am.mse.eduFood.domain.ERole;
import am.mse.eduFood.domain.User;
import am.mse.eduFood.dto.UserDto;
import am.mse.eduFood.service.UserService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;

@CrossOrigin(origins = {"https://edufood.mskh.am", "http://localhost:4200"})
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

    @PostMapping("/create")
    ResponseEntity<UserDto> newUser(@RequestBody
        User newUser) {
        UserDto existingUser = userService.getUserByUsername(newUser.getUsername());
        if(existingUser == null){
            newUser.setRole(ERole.CUSTOMER);
            return new ResponseEntity<>(userService.addUser(newUser), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PostMapping("/create/admin")
    ResponseEntity<UserDto> newAdminUser(@RequestBody
        User newUser) {
        UserDto existingUser = userService.getUserByUsername(newUser.getUsername());
        if(existingUser == null){
            newUser.setRole(ERole.ADMIN);
            newUser.setValid(true);
            return new ResponseEntity<>(userService.addUser(newUser), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @GetMapping("/{id}")
    UserDto one(@PathVariable
        Long id) throws NotFoundException {

        return userService.getUserById(id);
    }

    @PutMapping("/validate/{id}")
    UserDto validate(@PathVariable
        Long id) throws NotFoundException {
        return userService.validate(id);
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
