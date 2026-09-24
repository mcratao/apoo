package br.com.javaflix.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sede {
    private final String[] vagasGaragem = new String[3];
    private final List<Credencial> historicoCatraca = new ArrayList<>();
    private final Set<Credencial> acessoCofre = new HashSet<>();

    public void estacionarVeiculo(String placa, int vaga) {
        vagasGaragem[vaga] = placa;
        System.out.println("Veículo " + placa + " estacionado na vaga " + vaga);
    }

    public void registrarPassagemCatraca(Credencial c) {
        historicoCatraca.add(c);
        System.out.println("Passagem registrada: " + c);
    }

    public void autorizarEntradaCofre(Credencial c) {
        if (!acessoCofre.add(c)) {
            System.out.println("Alerta de Segurança: Tentativa de acesso duplicado com credencial já ativa no cofre");
        } else {
            System.out.println("Entrada no cofre autorizada para " + c);
        }
    }

    public int getQuantidadePassagens() { return historicoCatraca.size(); }
}
