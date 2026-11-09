package ec.edu.ups.vista;

import ec.edu.ups.modelo.Partido;
import java.util.List;
import java.util.Scanner;

public class ConsolaVista {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n===== TORNEO DE FÚTBOL MVC =====");
        System.out.println("1. Registrar equipos iniciales (16 equipos)");
        System.out.println("2. Realizar sorteo recursivo de partidos");
        System.out.println("3. Guardar torneo en archivo (.ser)");
        System.out.println("4. Cargar torneo desde archivo");
        System.out.println("5. Mostrar resultados de las etapas");
        System.out.println("6. Salir");
        System.out.print("Elija una opción: ");
        return scanner.nextInt();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarPartidos(String etapa, List<Partido> partidos) {
        System.out.println("\n--- PARTIDOS DE " + etapa.toUpperCase() + " ---");
        if (partidos.isEmpty()) {
            System.out.println("No hay partidos registrados para esta etapa todavía.");
        } else {
            for (Partido p : partidos) {
                System.out.println(p);
            }
        }
    }
}