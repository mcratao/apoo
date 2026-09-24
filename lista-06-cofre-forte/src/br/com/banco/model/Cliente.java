package br.com.banco.model;

import java.util.Objects;

public class Cliente {
    private final String cpf;
    private String nome;
    private String email;

    public Cliente(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Cliente outro)) return false;
        return Objects.equals(cpf, outro.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + " | Contato: " + email;
    }
}
