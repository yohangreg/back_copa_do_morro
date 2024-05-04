package br.com.copadomorro.copadomorro.controller;

import br.com.copadomorro.copadomorro.dto.AuthenticationDTO;
import br.com.copadomorro.copadomorro.dto.UserDTO;
import br.com.copadomorro.copadomorro.service.AuthService;
import br.com.copadomorro.copadomorro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/createUser")
    public ResponseEntity<?> insertNewUser(@RequestBody UserDTO newUser) {
        try {
            UserDTO userInsert = userService.insert(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDto) {
        return ResponseEntity.ok(authService.login(authDto));
    }

}
