package br.com.copadomorro.copadomorro.dto;

public class AcessDTO {

    private String token;

    //TODO: implementar retornar o usuário e liberações (authorities)

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AcessDTO(String token) {
        this.token = token;
    }
}
