package br.com.enigma.decoder;

public class DecodificadorReverso implements Decodificador {
    @Override
    public String decodificar(String texto) {
        return new StringBuilder(texto).reverse().toString();
    }
}
