package br.com.javaflix.model;

public class PagamentoCartao implements Pagamento {
    @Override
    public void processar(double valor) {
        System.out.printf("Validando limite no cartão para cobrança de R$ %.2f%n", valor);
    }
}
