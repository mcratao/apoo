package br.com.javaflix.model;

public class Video {
    private String titulo;
    private int duracaoMinutos;

    public Video(String titulo, int duracaoMinutos) {
        this.titulo = titulo;
        setDuracaoMinutos(duracaoMinutos);
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos <= 0 ? 1 : duracaoMinutos;
    }

    @Override
    public String toString() { return titulo + " (" + duracaoMinutos + " min)"; }
}
