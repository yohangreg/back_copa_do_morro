package br.com.copadomorro.backend.controller;

import br.com.copadomorro.backend.dto.UserDTO;
import br.com.copadomorro.backend.dto.UserUpdateDTO;
import br.com.copadomorro.backend.dto.UserViewDTO;
import br.com.copadomorro.backend.exceptions.UserServiceException;
import br.com.copadomorro.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserViewDTO> users = userService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " " + e.getCause());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userDTO) {
        try {
            UserViewDTO userUpdated = userService.update(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " " + e.getCause());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso");
        } catch (UserServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage() + " " + e.getCause());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        try {
            UserViewDTO userDTO = userService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (UserServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage() + " " + e.getCause());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<?> findUserByEmail(@RequestParam String email) {
        try {
            UserViewDTO userDTO = userService.findByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (UserServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage() + " " + e.getCause());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @GetMapping("/findByCpf")
    public ResponseEntity<?> findUserByCpf(@RequestParam String cpf) {
        try {
            UserViewDTO userDTO = userService.findByCpf(cpf);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (UserServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage() + " " + e.getCause());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }

    @GetMapping("/findByCnpj")
    public ResponseEntity<?> findUserByCnpj(@RequestParam String cnpj) {
        try {
            UserViewDTO userDTO = userService.findByCnpj(cnpj);
            return ResponseEntity.status(HttpStatus.OK).body(userDTO);
        } catch (UserServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage() + " " + e.getCause());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        }
    }
}
