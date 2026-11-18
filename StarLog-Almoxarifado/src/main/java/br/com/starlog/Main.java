package br.com.starlog;

import br.com.starlog.dao.ProdutoDAO;
import br.com.starlog.dao.ProdutoDAOSqlite;
import br.com.starlog.model.Produto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

/** Demonstracao completa do CRUD em um arquivo SQLite novo por execucao. */
public class Main {
    public static void main(String[] args) {
        // Evita interferencia de dados de execucoes anteriores.
        // Cada rodada cria um banco SQLite REAL e independente.
        try {
            Path banco = Files.createTempFile("starlog-demo-", ".db");
            System.setProperty("starlog.jdbc.url", "jdbc:sqlite:" + banco.toAbsolutePath());
            System.out.println("Banco SQLite da demonstracao: " + banco.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Nao foi possivel criar o banco de demonstracao.", e);
        }

        ProdutoDAO dao = new ProdutoDAOSqlite();

        // CREATE: o banco gera os IDs e os devolve a cada objeto.
        System.out.println("\n[CREATE] Cadastrando produtos");
        Produto teclado = new Produto("Teclado Mecânico RGB", 349.90, 10);
        Produto mousepad = new Produto("Mousepad Speed XL", 79.90, 20);
        dao.salvar(teclado);
        dao.salvar(mousepad);
        System.out.println("Teclado cadastrado com ID: " + teclado.getId());
        System.out.println("Mousepad cadastrado com ID: " + mousepad.getId());

        // READ: produtos ordenados por nome, exibidos em colunas.
        System.out.println("\n[READ] Todos os produtos");
        mostrarTabela(dao.listarTodos());

        // UPDATE: altera preco e estoque e confirma o retorno booleano.
        System.out.println("\n[UPDATE] Atualizando o teclado");
        teclado.setEstoque(15);
        teclado.setPreco(289.90);
        boolean atualizado = dao.atualizar(teclado);
        System.out.println("Atualizacao bem-sucedida? " + atualizado);
        dao.buscarPorId(teclado.getId()).ifPresentOrElse(
                p -> System.out.println("Registro atualizado: " + p),
                () -> System.out.println("Teclado nao encontrado."));

        // DELETE: exclusao existente e tentativa de ID inexistente.
        System.out.println("\n[DELETE] Excluindo o mousepad");
        boolean excluido = dao.excluir(mousepad.getId());
        System.out.println("Mousepad excluido? " + excluido);
        boolean inexistente = dao.excluir(888L);
        System.out.println("Exclusao do ID 888: " + inexistente
                + (inexistente ? " (registro excluido)" : " (ID nao encontrado; sem erro)"));

        // FINAL STATE: exatamente um produto.
        System.out.println("\n[FINAL STATE] Produtos restantes");
        List<Produto> restantes = dao.listarTodos();
        mostrarTabela(restantes);
        System.out.println("Total de produtos no banco: " + restantes.size());
        if (restantes.size() != 1 || !restantes.get(0).getId().equals(teclado.getId())) {
            throw new IllegalStateException("O estado final nao corresponde ao esperado.");
        }
        System.out.println("Validacao final: exatamente 1 produto, o teclado.");
    }

    private static void mostrarTabela(List<Produto> produtos) {
        System.out.printf("%-6s | %-27s | %12s | %7s%n", "ID", "NOME", "PRECO (R$)", "ESTOQUE");
        System.out.println("-".repeat(63));
        for (Produto p : produtos) {
            System.out.printf(Locale.forLanguageTag("pt-BR"),
                    "%-6d | %-27s | %12.2f | %7d%n",
                    p.getId(), p.getNome(), p.getPreco(), p.getEstoque());
        }
    }
}
