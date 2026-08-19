package br.com.techcorp.main;

import br.com.techcorp.model.Funcionario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControleDeAcesso {

    private List<Funcionario> historicoCatraca;
    private Set<Funcionario> autorizadosSalaSegura;

    public ControleDeAcesso() {
        historicoCatraca = new ArrayList<>();
        autorizadosSalaSegura = new HashSet<>();
    }

    public void registrarPassagem(Funcionario f) {
        historicoCatraca.add(f);
    }

    public void concederAcessoSala(Funcionario f) {
        boolean adicionado = autorizadosSalaSegura.add(f);

        if (adicionado) {
            System.out.println("Acesso liberado para a Sala Segura");
        } else {
            System.out.println(
                "Aviso: Matrícula já registrada na sala. Acesso não permitido."
            );
        }
    }
}
