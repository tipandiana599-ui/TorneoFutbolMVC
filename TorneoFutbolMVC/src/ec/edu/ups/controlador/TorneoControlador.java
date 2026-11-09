package ec.edu.ups.controlador;

import ec.edu.ups.modelo.*;
import ec.edu.ups.vista.ConsolaVista;
import java.io.IOException;
import java.util.Arrays;

public class TorneoControlador {
    private Torneo torneo;
    private ConsolaVista vista;
    private final String RUTA_ARCHIVO = "torneo.ser";

    public TorneoControlador(Torneo torneo, ConsolaVista vista) {
        this.torneo = torneo;
        this.vista = vista;
    }

    public void iniciar() {
        int opcion = 0;
        do {
            try {
                opcion = vista.mostrarMenu();
                switch (opcion) {
                    case 1:
                        torneo.registrarEquiposIniciales();
                        vista.mostrarMensaje("¡Se han registrado exitosamente los 16 equipos para el torneo!");
                        break;
                    case 2:
                        try {
                            // Creamos una lista temporal de 16 equipos para iniciar el sorteo recursivo
                            java.util.List<Equipo> listaTemp = Arrays.asList(
                                new Equipo("Eq 1"), new Equipo("Eq 2"), new Equipo("Eq 3"), new Equipo("Eq 4"),
                                new Equipo("Eq 5"), new Equipo("Eq 6"), new Equipo("Eq 7"), new Equipo("Eq 8"),
                                new Equipo("Eq 9"), new Equipo("Eq 10"), new Equipo("Eq 11"), new Equipo("Eq 12"),
                                new Equipo("Eq 13"), new Equipo("Eq 14"), new Equipo("Eq 15"), new Equipo("Eq 16")
                            );
                            torneo.sortearTorneoRecursivo(listaTemp, "Octavos");
                            vista.mostrarMensaje("¡Sorteo recursivo realizado con éxito!");
                        } catch (NumeroEquiposInvalidoException | SorteoInvalidoException e) {
                            vista.mostrarMensaje("Error en el sorteo: " + e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            torneo.guardarTorneo(RUTA_ARCHIVO);
                            vista.mostrarMensaje("Torneo guardado correctamente en " + RUTA_ARCHIVO);
                        } catch (IOException e) {
                            vista.mostrarMensaje("Error de E/S al guardar el archivo: " + e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            Torneo torneoCargado = Torneo.cargarTorneo(RUTA_ARCHIVO);
                            if (torneoCargado != null) {
                                this.torneo = torneoCargado;
                                vista.mostrarMensaje("Torneo cargado correctamente desde " + RUTA_ARCHIVO);
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            vista.mostrarMensaje("Error al cargar el archivo serializado: " + e.getMessage());
                        }
                        break;
                    case 5:
                        vista.mostrarPartidos("Octavos", torneo.getPartidosOctavos());
                        vista.mostrarPartidos("Cuartos", torneo.getPartidosCuartos());
                        vista.mostrarPartidos("Semifinal", torneo.getPartidosSemifinal());
                        vista.mostrarPartidos("Final", torneo.getPartidosFinal());
                        break;
                    case 6:
                        vista.mostrarMensaje("¡Saliendo de la aplicación del Torneo de Fútbol!");
                        break;
                    default:
                        vista.mostrarMensaje("Opción inválida. Intente de nuevo.");
                }
            } catch (Exception e) {
                vista.mostrarMensaje("Ocurrió un error en la entrada de datos. Por favor, ingrese un número válido.");
            }
        } while (opcion != 6);
    }
}