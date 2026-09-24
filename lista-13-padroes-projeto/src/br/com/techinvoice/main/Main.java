package br.com.techinvoice.main;

import br.com.techinvoice.config.GerenciadorConfiguracao;
import br.com.techinvoice.document.IDocumento;
import br.com.techinvoice.factory.DocumentoFactory;

public class Main {
    public static void main(String[] args) {
        GerenciadorConfiguracao gen1 = GerenciadorConfiguracao.getInstance();
        GerenciadorConfiguracao gen2 = GerenciadorConfiguracao.getInstance();

        System.out.println("API Key: " + gen1.getApiKey());
        if (gen1 == gen2) {
            System.out.println("Singleton validado: as duas referências apontam para a mesma instância.");
        }

        IDocumento nf = DocumentoFactory.criarDocumento("NF");
        nf.gerarPDF();

        try {
            DocumentoFactory.criarDocumento("BOLETO").gerarPDF();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro amigável: " + e.getMessage());
        }
    }
}
