package br.com.bookstore.main;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        LivroDAO dao = new LivroDAOSqlite();

        System.out.println("\n=== CADASTRANDO LIVROS ===");

        Livro livro1 = new Livro("Clean Code", "Robert C. Martin", 120.00);
        Livro livro2 = new Livro("Design Patterns", "Erich Gamma", 150.00);
        Livro livro3 = new Livro("Refactoring", "Martin Fowler", 135.00);

        dao.salvar(livro1);
        dao.salvar(livro2);
        dao.salvar(livro3);

        System.out.println("Livro cadastrado: " + livro1);
        System.out.println("Livro cadastrado: " + livro2);
        System.out.println("Livro cadastrado: " + livro3);

        System.out.println("\n=== BUSCANDO LIVRO ID 2 ===");
        dao.buscarPorId(2L).ifPresentOrElse(
            System.out::println,
            () -> System.out.println("Livro não encontrado.")
        );

        System.out.println("\n=== TESTANDO ID INEXISTENTE ===");
        dao.buscarPorId(999L).ifPresentOrElse(
            System.out::println,
            () -> System.out.println(
                "Nenhum livro encontrado com o ID informado."
            )
        );

        System.out.println("\n=== CATÁLOGO COMPLETO ===");
        List<Livro> livros = dao.listarTodos();

        System.out.printf("%-5s %-25s %-25s %10s%n",
                "ID", "TÍTULO", "AUTOR", "PREÇO");
        System.out.println(
                "--------------------------------------------------------------------------"
        );

        for (Livro livro : livros) {
            System.out.printf("%-5d %-25s %-25s R$ %8.2f%n",
                    livro.getId(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getPreco());
        }

        System.out.println("\n=== FIM DO PROGRAMA ===");
    }
}
