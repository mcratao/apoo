package br.com.enigma.main;

import br.com.enigma.decoder.DecodificadorBase64;
import br.com.enigma.decoder.DecodificadorCesar;
import br.com.enigma.decoder.DecodificadorReverso;
import br.com.enigma.image.FiltroVermelhoMagico;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        Path entrada = Path.of("entrada");
        Path pista1 = entrada.resolve("pista_01.jpg");

        if (!Files.exists(pista1)) {
            System.out.println("Coloque os arquivos originais da Operação Enigma na pasta entrada/.");
            System.out.println("O projeto já contém os decodificadores e o filtro exigidos pelo UML.");
            return;
        }

        String cauda = extrairDadosAposFimJpeg(pista1);
        System.out.println("Dados ocultos após o JPEG: " + cauda);

        String pistaDecodificada = tentarBase64(cauda);
        System.out.println("Pista 1 decodificada: " + pistaDecodificada);

        Integer chave = extrairPrimeiroInteiro(pistaDecodificada);
        Path arquivoTexto = localizarArquivoTexto(entrada, pistaDecodificada);

        if (arquivoTexto != null && chave != null) {
            String conteudo = Files.readString(arquivoTexto, StandardCharsets.UTF_8).trim();
            String cesar = new DecodificadorCesar(chave).decodificar(conteudo);
            String reverso = new DecodificadorReverso().decodificar(cesar);
            System.out.println("Resultado César: " + cesar);
            System.out.println("Resultado César + reverso: " + reverso);
        } else {
            System.out.println("Não foi possível inferir automaticamente o arquivo/chave da segunda pista.");
        }

        Path imagemFinal = Files.list(entrada)
                .filter(p -> p.getFileName().toString().toLowerCase().matches(".*\\.(png|jpg|jpeg)$"))
                .filter(p -> !p.equals(pista1))
                .findFirst().orElse(null);
        if (imagemFinal != null) {
            Path saida = Path.of("saida_filtro_vermelho.png");
            new FiltroVermelhoMagico().aplicarFiltro(imagemFinal.toString(), saida.toString());
            System.out.println("Imagem filtrada salva em: " + saida.toAbsolutePath());
        }
    }

    private static String extrairDadosAposFimJpeg(Path arquivo) throws IOException {
        byte[] dados = Files.readAllBytes(arquivo);
        for (int i = 0; i < dados.length - 1; i++) {
            if ((dados[i] & 0xFF) == 0xFF && (dados[i + 1] & 0xFF) == 0xD9) {
                int inicio = i + 2;
                if (inicio >= dados.length) return "";
                return new String(dados, inicio, dados.length - inicio, StandardCharsets.UTF_8).trim();
            }
        }
        throw new IOException("Marcador EOI do JPEG não encontrado.");
    }

    private static String tentarBase64(String texto) {
        try {
            return new DecodificadorBase64().decodificar(texto);
        } catch (IllegalArgumentException e) {
            return texto;
        }
    }

    private static Integer extrairPrimeiroInteiro(String texto) {
        Matcher m = Pattern.compile("-?\\d+").matcher(texto);
        return m.find() ? Integer.parseInt(m.group()) : null;
    }

    private static Path localizarArquivoTexto(Path pasta, String pista) throws IOException {
        Matcher m = Pattern.compile("([A-Za-z0-9_.-]+\\.(?:txt|dat|enc))", Pattern.CASE_INSENSITIVE).matcher(pista);
        if (m.find()) {
            Path candidato = pasta.resolve(m.group(1));
            if (Files.exists(candidato)) return candidato;
        }
        return Files.list(pasta)
                .filter(p -> p.getFileName().toString().toLowerCase().matches(".*\\.(txt|dat|enc)$"))
                .findFirst().orElse(null);
    }
}
