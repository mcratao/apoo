package br.com.ecommerce.main;

import br.com.ecommerce.exception.TipoFreteInvalidoException;
import br.com.ecommerce.model.CalculadoraFrete;
import br.com.ecommerce.model.FreteMotoboy;
import br.com.ecommerce.model.FretePac;
import br.com.ecommerce.model.FreteSedex;

public class MainTeste {
    public static void main(String[] args) {
        CalculadoraFrete calculadora = new CalculadoraFrete();
        double pedido = 100.0;

        try {
            System.out.printf("SEDEX: R$ %.2f%n", calculadora.processarFrete(pedido, new FreteSedex()));
            System.out.printf("PAC: R$ %.2f%n", calculadora.processarFrete(pedido, new FretePac()));
            System.out.printf("MOTOBOY: R$ %.2f%n", calculadora.processarFrete(pedido, new FreteMotoboy()));
            calculadora.processarFrete(pedido, null);
        } catch (TipoFreteInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }
}
