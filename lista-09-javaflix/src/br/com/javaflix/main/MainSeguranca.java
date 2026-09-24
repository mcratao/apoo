package br.com.javaflix.main;

import br.com.javaflix.model.Credencial;
import br.com.javaflix.model.Sede;

public class MainSeguranca {
    public static void main(String[] args) {
        Sede sede = new Sede();
        Credencial c1 = new Credencial("A1B2C3");

        sede.registrarPassagemCatraca(c1);
        sede.registrarPassagemCatraca(c1);
        System.out.println("Passagens na catraca: " + sede.getQuantidadePassagens());

        sede.autorizarEntradaCofre(c1);
        sede.autorizarEntradaCofre(new Credencial("A1B2C3"));

        sede.estacionarVeiculo("AAA1A11", 0);
        sede.estacionarVeiculo("BBB2B22", 1);
        sede.estacionarVeiculo("CCC3C33", 2);
        sede.estacionarVeiculo("DDD4D44", 3); // exceção proposital
    }
}
