package am.mse.eduFood.service;

import am.mse.eduFood.domain.User;
import am.mse.eduFood.dto.UserDto;
import am.mse.eduFood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
        @Qualifier("passwordEncoder")
            PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public void addUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        return userRepository.findAll().stream()
            .map(u -> new UserDto(u.getFirstName(), u.getLastName(), u.getUsername(), u.getRole())).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id).orElse(null);

        //TODO come back to throw exception
        if (user == null) {
            return null;
        }
        return new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(), user.getRole());

    }

    @Override
    public UserDto getUserByUsername(String username) {

        User user = userRepository.findByUsername(username);
        return new UserDto(user.getFirstName(), user.getLastName(), user.getUsername(), user.getRole());
    }


    @Override
    public void deleteUserById(Long id) {

        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }

    }


}
