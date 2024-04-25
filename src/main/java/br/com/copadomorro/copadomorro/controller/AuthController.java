package br.com.copadomorro.copadomorro.controller;

import br.com.copadomorro.copadomorro.dto.AuthenticationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDto) {

    }

}
