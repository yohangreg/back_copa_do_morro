package br.com.copadomorro.copadomorro.controller;

import br.com.copadomorro.copadomorro.dto.UserDTO;
import br.com.copadomorro.copadomorro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity saveUser() {
        return ResponseEntity.ok("Usuário cadastrado");
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
}
