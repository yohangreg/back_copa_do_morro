package br.com.copadomorro.copadomorro.service;

import br.com.copadomorro.copadomorro.entity.User;
import br.com.copadomorro.copadomorro.entity.UserDetailsImpl;
import br.com.copadomorro.copadomorro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).get();
        return UserDetailsImpl.build(user);
    }
}
