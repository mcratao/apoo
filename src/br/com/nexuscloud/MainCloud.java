package br.com.nexuscloud;

public class MainCloud {
    public static void main(String[] args) {
        CloudManager manager = new CloudManager();

        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);

        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);

        Container container1 = new Container("C-01", "Aplicacao 1", 10.0);
        Container container2 = new Container("C-02", "Aplicacao 2", 8.0);

        // Teste da exceção unchecked: descomente para verificar e depois comente novamente.
        // servidor.alocarContainer(container1);

        servidor.ligar();

        try {
            servidor.alocarContainer(container1);
            System.out.println("Container 1 alocado com sucesso.");
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        try {
            servidor.alocarContainer(container2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        System.out.printf("Fatura ativa: R$ %.2f%n", manager.calcularFaturaAtiva(cliente));
    }
}
// RA: 25102653-2
