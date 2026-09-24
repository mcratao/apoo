package br.com.banco.main;

import br.com.banco.model.Agencia;
import br.com.banco.model.Cliente;
import br.com.banco.model.ContaBancaria;

public class MainTeste {
    public static void main(String[] args) {
        Cliente c1 = new Cliente("123.456.789-00", "Ana", "ana@email.com");
        Cliente c2 = new Cliente("123.456.789-00", "Ana Silva", "ana.silva@email.com");

        if (c1.equals(c2)) {
            System.out.println("Clientes iguais pelo CPF.");
        } else {
            System.out.println("Falha: clientes deveriam ser iguais.");
        }

        ContaBancaria conta = new ContaBancaria("0001", c1, 50.0);
        System.out.println("Saque de R$ 50,00 realizado? " + conta.sacar(50.0));
        System.out.println("Saldo final: R$ " + conta.getSaldo());
        System.out.println("Banco: " + Agencia.NOME_BANCO);
        System.out.println("Total de contas abertas: " + Agencia.getTotalContasAbertas());
    }
}
