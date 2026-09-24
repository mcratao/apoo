package br.com.javaflix.model;

public class PagamentoPix implements Pagamento {
    @Override
    public void processar(double valor) {
        System.out.printf("Gerando QR Code Pix no valor de R$ %.2f%n", valor);
    }
}
