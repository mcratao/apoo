package br.com.javaflix.model;

import java.util.Objects;

public class Credencial {
    private final String codigoHex;

    public Credencial(String codigoHex) { this.codigoHex = codigoHex; }
    public String getCodigoHex() { return codigoHex; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Credencial outra)) return false;
        return Objects.equals(codigoHex, outra.codigoHex);
    }

    @Override
    public int hashCode() { return Objects.hash(codigoHex); }

    @Override
    public String toString() { return "Credencial{" + codigoHex + "}"; }
}
