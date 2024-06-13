package br.com.copadomorro.backend.controller;

import br.com.copadomorro.backend.dto.*;
import br.com.copadomorro.backend.service.AuthService;
import br.com.copadomorro.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/singUp")
    public ResponseEntity<?> insertNewUser(@RequestBody NewUserDTO newUser) {
        try {
            UserViewDTO userInsert = userService.insert(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(userInsert);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage() + " " + ex.getCause());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage() + " " + ex.getCause());
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDto) {
        try {
            AcessDTO token = authService.login(authDto);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage() + " " + ex.getCause());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage() + " " + ex.getCause());
        }
    }

}
