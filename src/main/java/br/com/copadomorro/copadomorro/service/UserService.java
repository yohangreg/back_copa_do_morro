package br.com.copadomorro.copadomorro.service;

import br.com.copadomorro.copadomorro.dto.UserDTO;
import br.com.copadomorro.copadomorro.entity.User;
import br.com.copadomorro.copadomorro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void insert(UserDTO userDTO) {
        User user = new User(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
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
}
