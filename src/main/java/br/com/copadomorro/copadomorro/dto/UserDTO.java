package br.com.copadomorro.copadomorro.dto;

import br.com.copadomorro.copadomorro.entity.User;
import br.com.copadomorro.copadomorro.entity.enums.TypeUserEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String cnpj;
    private TypeUserEnum type;

    public UserDTO(User user) {
        BeanUtils.copyProperties(user, this);
    }

    public UserDTO() {
    }

}
