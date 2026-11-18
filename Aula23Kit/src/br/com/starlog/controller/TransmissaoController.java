package br.com.starlog.controller;

import br.com.starlog.model.CriptoMensagem;
import java.util.ArrayList;
import java.util.List;

/** Coordena o processamento da transmissão sem depender da interface gráfica. */
public class TransmissaoController {
    private final List<String> bufferTransmissoes = new ArrayList<>();

    public String processarTransmissao(String remetenteRaw, String msgRaw, String chaveStr) {
        int chave;
        try {
            chave = Integer.parseInt(chaveStr.trim());
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException("Chave deve ser um número inteiro válido.", e);
        }

        CriptoMensagem cripto = new CriptoMensagem(remetenteRaw, msgRaw, chave);
        String textoCifrado = cripto.cifrar();
        int checksum = cripto.calcularChecksum();
        String pacote = String.format("[%s] #%d -> %s", cripto.getRemetente(), checksum, textoCifrado);
        bufferTransmissoes.add(pacote);
        return pacote;
    }

    public int getTotalTransmissoes() {
        return bufferTransmissoes.size();
    }
}
