package br.com.enigma.decoder;

public class DecodificadorCesar implements Decodificador {
    private final int chave;

    public DecodificadorCesar(int chave) {
        this.chave = Math.floorMod(chave, 26);
    }

    @Override
    public String decodificar(String texto) {
        StringBuilder sb = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                sb.append((char) ('A' + Math.floorMod(c - 'A' - chave, 26)));
            } else if (c >= 'a' && c <= 'z') {
                sb.append((char) ('a' + Math.floorMod(c - 'a' - chave, 26)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
