package br.com.techinvoice.config;

public class GerenciadorConfiguracao {
    private String apiKey = "AWS-12345-KEY";
    private static GerenciadorConfiguracao instancia;

    private GerenciadorConfiguracao() { }

    public static GerenciadorConfiguracao getInstance() {
        if (instancia == null) {
            instancia = new GerenciadorConfiguracao();
        }
        return instancia;
    }

    public String getApiKey() { return apiKey; }
}
