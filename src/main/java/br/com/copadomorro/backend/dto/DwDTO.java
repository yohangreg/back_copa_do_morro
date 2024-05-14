package br.com.copadomorro.backend.dto;

import br.com.copadomorro.backend.entity.Dw;
import br.com.copadomorro.backend.entity.User;
import jakarta.persistence.Column;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class DwDTO {

    private Long id;
    private String nomeJogador;
    private String cpfJogador;
    private Date dataNascimentoJogador;
    private String estadoJogador;
    private String cidadeJogador;
    private String comunidadeJogador;
    private String nomeJogo;
    private Date dataJogo;
    private String estadoJogo;
    private String cidadeJogo;
    private String comunidadeJogo;
    private String nomeResponsavel;
    private String cpfResponsavel;
    private String emailResponsavel;

    public DwDTO(Dw dw) {
        BeanUtils.copyProperties(dw, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public String getCpfJogador() {
        return cpfJogador;
    }

    public void setCpfJogador(String cpfJogador) {
        this.cpfJogador = cpfJogador;
    }

    public Date getDataNascimentoJogador() {
        return dataNascimentoJogador;
    }

    public void setDataNascimentoJogador(Date dataNascimentoJogador) {
        this.dataNascimentoJogador = dataNascimentoJogador;
    }

    public String getEstadoJogador() {
        return estadoJogador;
    }

    public void setEstadoJogador(String estadoJogador) {
        this.estadoJogador = estadoJogador;
    }

    public String getCidadeJogador() {
        return cidadeJogador;
    }

    public void setCidadeJogador(String cidadeJogador) {
        this.cidadeJogador = cidadeJogador;
    }

    public String getComunidadeJogador() {
        return comunidadeJogador;
    }

    public void setComunidadeJogador(String comunidadeJogador) {
        this.comunidadeJogador = comunidadeJogador;
    }

    public String getNomeJogo() {
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo) {
        this.nomeJogo = nomeJogo;
    }

    public Date getDataJogo() {
        return dataJogo;
    }

    public void setDataJogo(Date dataJogo) {
        this.dataJogo = dataJogo;
    }

    public String getEstadoJogo() {
        return estadoJogo;
    }

    public void setEstadoJogo(String estadoJogo) {
        this.estadoJogo = estadoJogo;
    }

    public String getCidadeJogo() {
        return cidadeJogo;
    }

    public void setCidadeJogo(String cidadeJogo) {
        this.cidadeJogo = cidadeJogo;
    }

    public String getComunidadeJogo() {
        return comunidadeJogo;
    }

    public void setComunidadeJogo(String comunidadeJogo) {
        this.comunidadeJogo = comunidadeJogo;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCpfResponsavel() {
        return cpfResponsavel;
    }

    public void setCpfResponsavel(String cpfResponsavel) {
        this.cpfResponsavel = cpfResponsavel;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public void setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
    }
}
