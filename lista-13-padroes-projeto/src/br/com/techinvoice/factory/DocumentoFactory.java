package br.com.techinvoice.factory;

import br.com.techinvoice.document.IDocumento;
import br.com.techinvoice.document.NotaFiscal;
import br.com.techinvoice.document.Recibo;

public class DocumentoFactory {
    private DocumentoFactory() { }

    public static IDocumento criarDocumento(String tipo) {
        if ("NF".equalsIgnoreCase(tipo)) return new NotaFiscal();
        if ("RECIBO".equalsIgnoreCase(tipo)) return new Recibo();
        throw new IllegalArgumentException("Documento inválido: " + tipo);
    }
}
