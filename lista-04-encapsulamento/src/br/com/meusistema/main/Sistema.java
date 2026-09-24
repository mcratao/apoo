package br.com.meusistema.main;

import br.com.meusistema.model.Carro;

public class Sistema {
    public static void main(String[] args) {
        Carro meuCarro = new Carro();
        meuCarro.setModelo("Fusca");
        meuCarro.setCor("Azul");
        meuCarro.setVelocidade(-500);
        meuCarro.buzinar();
        System.out.println("Velocidade armazenada: " + meuCarro.getVelocidade() + " km/h");
    }
}
