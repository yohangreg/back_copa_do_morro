package br.com.copadomorro.backend.service;

import br.com.copadomorro.backend.dto.UserDTO;
import br.com.copadomorro.backend.dto.UserViewDTO;
import br.com.copadomorro.backend.entity.User;
import br.com.copadomorro.backend.exceptions.UserServiceException;
import br.com.copadomorro.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserViewDTO insert(UserDTO userDTO) {
        try {
            validateUser(userDTO);
            User user = new User(userDTO);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            User savedUser = userRepository.save(user);
            return new UserViewDTO(savedUser);
        } catch (Exception e) {
            throw new UserServiceException("Erro ao criar novo usuário", e);
        }
    }

    public List<UserViewDTO> findAll() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream().map(UserViewDTO::new).collect(Collectors.toList());
        } catch (Exception e) {
            throw new UserServiceException("Erro ao buscar todos os usuários", e);
        }
    }

    public UserViewDTO update(UserDTO userDTO) {
        try {
            User existingUser = userRepository.findByEmail(userDTO.getEmail()).get();
            if (existingUser == null) {
                throw new UserServiceException("Usuário não encontrado para o e-mail fornecido: " + userDTO.getEmail());
            }

            // Atualiza os campos do usuário com os valores do DTO, exceto a senha
            existingUser.setName(userDTO.getName());
            existingUser.setCpf(userDTO.getCpf());
            existingUser.setCnpj(userDTO.getCnpj());
            existingUser.setType(userDTO.getType());

            // Salva as alterações no usuário
            User updatedUser = userRepository.save(existingUser);

            // Retorna uma visualização do usuário atualizado
            return new UserViewDTO(updatedUser);
        } catch (Exception e) {
            throw new UserServiceException("Erro ao atualizar usuário", e);
        }
    }


    public void delete(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserServiceException("Usuário não encontrado para o ID: " + id));
            userRepository.delete(user);
        } catch (Exception e) {
            throw new UserServiceException("Erro ao excluir usuário", e);
        }
    }

    public UserViewDTO findById(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new UserServiceException("Usuário não encontrado para o ID: " + id));
            return new UserViewDTO(user);
        } catch (Exception e) {
            throw new UserServiceException("Erro ao buscar usuário por ID", e);
        }
    }

    public UserViewDTO findByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UserServiceException("Usuário não encontrado para o e-mail: " + email));
            return new UserViewDTO(user);
        } catch (Exception e) {
            throw new UserServiceException("Erro ao buscar usuário por e-mail", e);
        }
    }

    public UserViewDTO findByCpf(String cpf) {
        try {
            User user = userRepository.findByCpf(cpf).orElseThrow(() -> new UserServiceException("Usuário não encontrado para o cpf: " + cpf));
            return new UserViewDTO(user);
        } catch (Exception e) {
            throw new UserServiceException("Erro ao buscar usuário por cpf", e);
        }
    }

    public UserViewDTO findByCnpj(String cnpj) {
        try {
            User user = userRepository.findByCpf(cnpj).orElseThrow(() -> new UserServiceException("Usuário não encontrado para o cnpj: " + cnpj));
            return new UserViewDTO(user);
        } catch (Exception e) {
            throw new UserServiceException("Erro ao buscar usuário por cnpj", e);
        }
    }

    private void validateUser(UserDTO user) {
        if (user == null || hasInvalidField(user)) {
            throw new UserServiceException("Dados de usuário inválidos");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserServiceException("E-mail já cadastrado: " + user.getEmail());
        }

        if (!isNullOrEmpty(user.getCpf()) && userRepository.findByCpf(user.getCpf()).isPresent()) {
            throw new UserServiceException("Cpf já cadastrado: " + user.getCpf());
        }

        if (!isNullOrEmpty(user.getCnpj()) && userRepository.findByCnpj(user.getCnpj()).isPresent()) {
            throw new UserServiceException("Cnpj já cadastrado: " + user.getCnpj());
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
