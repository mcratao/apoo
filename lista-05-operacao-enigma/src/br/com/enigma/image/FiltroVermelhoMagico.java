package br.com.enigma.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FiltroVermelhoMagico implements FiltroImagem {
    @Override
    public void aplicarFiltro(String caminhoEntrada, String caminhoSaida) throws IOException {
        BufferedImage entrada = ImageIO.read(new File(caminhoEntrada));
        if (entrada == null) {
            throw new IOException("Imagem inválida: " + caminhoEntrada);
        }
        BufferedImage saida = new BufferedImage(entrada.getWidth(), entrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < entrada.getHeight(); y++) {
            for (int x = 0; x < entrada.getWidth(); x++) {
                Color cor = new Color(entrada.getRGB(x, y));
                int valor = cor.getRed();
                // Regra UML: canal vermelho -> branco; demais -> preto.
                int rgb = valor > 127 ? Color.WHITE.getRGB() : Color.BLACK.getRGB();
                saida.setRGB(x, y, rgb);
            }
        }
        ImageIO.write(saida, "png", new File(caminhoSaida));
    }
}
