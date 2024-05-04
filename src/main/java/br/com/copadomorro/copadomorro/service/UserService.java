package br.com.copadomorro.copadomorro.service;

import br.com.copadomorro.copadomorro.dto.UserDTO;
import br.com.copadomorro.copadomorro.entity.User;
import br.com.copadomorro.copadomorro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO insert(UserDTO userDTO) {
        validateUser(userDTO);
        User user = new User(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        if (savedUser == null) {
            throw new RuntimeException("Error creating new user");
        }
        return new UserDTO(savedUser);
    }

    public List<UserDTO> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO update(UserDTO userDTO) {
        User user = new User(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return new UserDTO(userRepository.save(user));
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    public UserDTO findById(Long id) {
        return new UserDTO(userRepository.findById(id).get());
    }

    public UserDTO findByEmail(String email) {
        return new UserDTO(userRepository.findByEmail(email).get());
    }

    private void validateUser(UserDTO user) {
        if (user == null || hasInvalidField(user)) {
            throw new IllegalArgumentException("Invalid user data");
        }
    }

    private boolean hasInvalidField(UserDTO userDTO) {
        if (isNullOrEmpty(userDTO.getName())
            || isNullOrEmpty(userDTO.getEmail())
            || isNullOrEmpty(userDTO.getPassword())
            || isNullOrEmpty(userDTO.getType())
            || (isNullOrEmpty(userDTO.getCnpj()) && isNullOrEmpty(userDTO.getCpf()))) {
            return true;
        }
        return false;
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
