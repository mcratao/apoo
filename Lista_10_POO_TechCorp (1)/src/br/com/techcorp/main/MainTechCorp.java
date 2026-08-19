package br.com.techcorp.main;

import br.com.techcorp.model.Funcionario;

public class MainTechCorp {

    public static void main(String[] args) {

        ControleDeAcesso controle = new ControleDeAcesso();

        Funcionario f1 = new Funcionario(
                "T-001",
                "Alice",
                "Analista"
        );

        Funcionario f2 = new Funcionario(
                "T-001",
                "Alice Duplicada",
                "Estagiária"
        );

        System.out.println("=== TESTE DA CATRACA ===");

        controle.registrarPassagem(f1);
        controle.registrarPassagem(f2);

        System.out.println("Duas passagens registradas.");

        System.out.println();
        System.out.println("=== TESTE DA SALA SEGURA ===");

        controle.concederAcessoSala(f1);
        controle.concederAcessoSala(f2);
    }
}
