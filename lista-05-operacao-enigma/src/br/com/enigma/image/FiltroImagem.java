package br.com.enigma.image;

import java.io.IOException;

public interface FiltroImagem {
    void aplicarFiltro(String caminhoEntrada, String caminhoSaida) throws IOException;
}
