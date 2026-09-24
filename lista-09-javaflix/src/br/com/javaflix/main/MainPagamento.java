package br.com.javaflix.main;

import br.com.javaflix.model.Pagamento;
import br.com.javaflix.model.PagamentoCartao;
import br.com.javaflix.model.PagamentoPix;
import br.com.javaflix.model.ProcessadorPagamento;

public class MainPagamento {
    public static void main(String[] args) {
        ProcessadorPagamento processador = new ProcessadorPagamento();
        Pagamento pix = new PagamentoPix();
        Pagamento cartao = new PagamentoCartao();

        processador.finalizarCompra(59.90, pix);
        processador.finalizarCompra(119.90, cartao);
    }
}
