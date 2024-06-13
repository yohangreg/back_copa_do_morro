package br.com.copadomorro.backend.entity;

import br.com.copadomorro.backend.dto.DwDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Entity
@Table(name = "dw")
public class Dw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_jogador", nullable = false)
    private String nomeJogador;

    @Column(name = "cpf_jogador", nullable = false)
    private String cpfJogador;

    @Column(name = "data_nascimento_jogador", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataNascimentoJogador;

    @Column(name = "estado_jogador", nullable = false)
    private String estadoJogador;

    @Column(name = "cidade_jogador", nullable = false)
    private String cidadeJogador;

    @Column(name = "comunidade_jogador")
    private String comunidadeJogador;

    @Column(name = "nome_jogo", nullable = false)
    private String nomeJogo;

    @Column(name = "data_jogo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataJogo;

    @Column(name = "estado_jogo", nullable = false)
    private String estadoJogo;

    @Column(name = "cidade_jogo", nullable = false)
    private String cidadeJogo;

    @Column(name = "comunidade_jogo")
    private String comunidadeJogo;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "cpf_responsavel")
    private String cpfResponsavel;

    @Column(name = "email_responsavel")
    private String emailResponsavel;

    public Dw(DwDTO dwDTO) {
        BeanUtils.copyProperties(dwDTO, this);
    }

    public Dw(){
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
