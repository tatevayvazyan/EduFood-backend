package am.mse.eduFood.service;

import am.mse.eduFood.domain.User;
import am.mse.eduFood.dto.UserDto;
import am.mse.eduFood.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
        @Qualifier("passwordEncoder")
            PasswordEncoder passwordEncoder) {

        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public UserDto addUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return getUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        return userRepository.findAll().stream().map(this::getUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) throws NotFoundException {

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return getUserDto(user);

    }

    @Override
    public UserDto getUserByUsername(String username) {

        User user = userRepository.findByUsername(username);
        return getUserDto(user);
    }


    @Override
    public void deleteUserById(Long id) {

        userRepository.findById(id).ifPresent(userRepository::delete);

    }

    @Override
    public UserDto getUserDto(User user) {

        if (user == null) {
            return null;
        }
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getRole(),
            user.getEmail(), user.getPhoneNumber(), user.getClassNumber(), user.isValid());
    }

    @Override
    public UserDto validate(Long id) throws NotFoundException {

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        user.setValid(true);
        userRepository.save(user);

        return getUserDto(user);
    }
}
