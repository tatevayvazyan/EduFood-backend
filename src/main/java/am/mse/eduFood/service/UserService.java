package am.mse.eduFood.service;


import am.mse.eduFood.domain.User;
import am.mse.eduFood.dto.UserDto;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void addUser(User user);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id) throws NotFoundException;
    UserDto getUserByUsername(String username);
    void deleteUserById(Long id);
    UserDto getUserDto(User user);
}
