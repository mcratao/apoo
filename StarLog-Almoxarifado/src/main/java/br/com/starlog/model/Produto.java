package br.com.starlog.model;

import java.util.Locale;

/** Entidade de dominio: regras validas independentemente do banco. */
public class Produto {
    private Long id;
    private String nome;
    private double preco;
    private int estoque;

    // Construtor de negocio: o ID sera atribuido pelo banco.
    public Produto(String nome, double preco, int estoque) {
        setNome(nome);
        setPreco(preco);
        setEstoque(estoque);
    }

    // Construtor de persistencia: reconstrucao a partir do banco.
    public Produto(Long id, String nome, double preco, int estoque) {
        this(nome, preco, estoque);
        setId(id);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }

    public void setId(Long id) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("O ID deve ser positivo.");
        }
        this.id = id;
    }

    private void setNome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome deve ter pelo menos 3 caracteres.");
        }
        this.nome = nome.trim();
    }

    public void setPreco(double preco) {
        if (!Double.isFinite(preco) || preco <= 0.0) {
            throw new IllegalArgumentException("O preco deve ser maior que zero e finito.");
        }
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("O estoque nao pode ser negativo.");
        }
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return String.format(Locale.forLanguageTag("pt-BR"),
                "Produto{id=%s, nome='%s', preco=R$ %.2f, estoque=%d}",
                id, nome, preco, estoque);
    }
}
