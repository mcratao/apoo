package br.com.nexuscloud;

import java.util.Objects;

public class Container {
    private String id;
    private String nomeApp;
    private double ramNecessaria;

    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = id;
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNomeApp() { return nomeApp; }
    public void setNomeApp(String nomeApp) { this.nomeApp = nomeApp; }

    public double getRamNecessaria() { return ramNecessaria; }
    public void setRamNecessaria(double ramNecessaria) { this.ramNecessaria = ramNecessaria; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Container)) return false;
        Container outro = (Container) obj;
        return Objects.equals(id, outro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
// RA: 25102653-2
