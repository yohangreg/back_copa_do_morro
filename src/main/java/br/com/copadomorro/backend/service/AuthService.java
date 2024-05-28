package br.com.copadomorro.backend.service;

import br.com.copadomorro.backend.dto.AcessDTO;
import br.com.copadomorro.backend.dto.AuthenticationDTO;
import br.com.copadomorro.backend.entity.UserDetailsImpl;
import br.com.copadomorro.backend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;
    public AcessDTO login(AuthenticationDTO authDto) {

        try {
            //Cria Mecanismo de credencial para o spring
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getPassword());

            //Prepara mecanismo para autenticacao
            Authentication authentication = authenticationManager.authenticate(userAuth);

            //Busca usuario logado
            UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

            AcessDTO acessDTO = new AcessDTO(token);

            return acessDTO;
        } catch (BadCredentialsException e) {
            //TODO: LOGIN OU SENHA INVALIDO
        }
        return new AcessDTO("Acesso negado");
    }
}
