package br.com.nexuscloud;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CloudManager {
    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    public void registrarCliente(Cliente cliente) {
        bancoDeDados.putIfAbsent(cliente, new HashSet<>());
    }

    public void provisionarServidor(Cliente cliente, Servidor servidor) {
        bancoDeDados.putIfAbsent(cliente, new HashSet<>());
        bancoDeDados.get(cliente).add(servidor);
    }

    public double calcularFaturaAtiva(Cliente cliente) {
        return bancoDeDados.getOrDefault(cliente, Set.of())
                .stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }

    public Map<Cliente, Set<Servidor>> getBancoDeDados() {
        return bancoDeDados;
    }
}
// RA: 25102653-2
