package br.com.fiap.to;

import java.time.LocalDate;

public class ClienteTO {
    private Long codigo;
    private String nome, cpf, email;

    public ClienteTO() {

    }

    public ClienteTO(Long codigo, String nome, String cpf, String email, LocalDate dataDeNascimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
