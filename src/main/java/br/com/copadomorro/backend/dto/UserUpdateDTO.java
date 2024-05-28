package br.com.copadomorro.backend.dto;

import br.com.copadomorro.backend.entity.User;
import br.com.copadomorro.backend.entity.enums.UserSituationType;
import org.springframework.beans.BeanUtils;

public class UserUpdateDTO {

    private String email;
    private String name;
    private String password;
    private String cpf;
    private String cnpj;
    private String type;

    public UserUpdateDTO(User user) {
        BeanUtils.copyProperties(user, this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
