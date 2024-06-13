package br.com.copadomorro.backend.dto;

import br.com.copadomorro.backend.entity.User;
import org.springframework.beans.BeanUtils;

public class NewUserDTO {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;

    public NewUserDTO(User user) {
        BeanUtils.copyProperties(user, this);
    }

    public NewUserDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
