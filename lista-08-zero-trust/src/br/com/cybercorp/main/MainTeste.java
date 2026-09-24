package br.com.cybercorp.main;

import br.com.cybercorp.model.Credencial;
import br.com.cybercorp.model.Departamento;
import br.com.cybercorp.model.Funcionario;
import br.com.cybercorp.model.SistemaSeguranca;
import br.com.cybercorp.model.Veiculo;

public class MainTeste {
    public static void main(String[] args) {
        Departamento depto = new Departamento("TI", "Tecnologia", 4);
        Funcionario func = new Funcionario("F001", "Marina", depto);
        Veiculo veiculo = new Veiculo("ABC1D23", "Gol", func);
        SistemaSeguranca sistema = new SistemaSeguranca(2);

        Credencial c1 = new Credencial("FFF-999", true, func);
        Credencial clone = new Credencial("FFF-999", true, func);

        sistema.registrarCatraca(func);
        sistema.registrarCatraca(func);
        sistema.acessarCofre(c1);
        sistema.acessarCofre(clone);
        sistema.estacionarVeiculo(veiculo, 0);

        // A lista pede que o erro seja provocado para demonstrar a rigidez do array.
        sistema.estacionarVeiculo(veiculo, 5);
    }
}
