package br.com.javaflix.main;

import br.com.javaflix.model.Usuario;

public class MainMemoria {
    public static void main(String[] args) {
        Usuario u1 = new Usuario("Ana", "mesmo@email.com", true);
        Usuario u2 = new Usuario("Ana Clone", "mesmo@email.com", false);

        if (u1.equals(u2)) {
            System.out.println("Usuários reconhecidos como logicamente iguais pelo e-mail.");
        } else {
            System.out.println("Usuários diferentes.");
        }

        System.out.println("Plataforma: " + Usuario.NOME_PLATAFORMA);
        System.out.println("Total de usuários: " + Usuario.getTotalUsuarios());
    }
}
