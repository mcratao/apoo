package br.com.javaflix.main;

import br.com.javaflix.model.Usuario;
import br.com.javaflix.model.Video;

public class MainStreaming {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Carlos", "carlos@email.com", true);
        Video video1 = new Video("Filme Java", 120);
        Video video2 = new Video("Vídeo inválido", -45);

        System.out.println(usuario);
        System.out.println(video1);
        System.out.println("Duração protegida do segundo vídeo: " + video2.getDuracaoMinutos() + " minuto(s)");
    }
}
