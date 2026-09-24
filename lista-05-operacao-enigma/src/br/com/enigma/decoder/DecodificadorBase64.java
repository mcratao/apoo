package br.com.enigma.decoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DecodificadorBase64 implements Decodificador {
    @Override
    public String decodificar(String texto) {
        byte[] bytes = Base64.getDecoder().decode(texto.replaceAll("\s+", ""));
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
