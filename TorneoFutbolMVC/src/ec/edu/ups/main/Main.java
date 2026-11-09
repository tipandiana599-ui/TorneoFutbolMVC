package ec.edu.ups.main;

import ec.edu.ups.controlador.TorneoControlador;
import ec.edu.ups.modelo.Torneo;
import ec.edu.ups.vista.ConsolaVista;

public class Main {
    public static void main(String[] args) {
        Torneo torneo = new Torneo();
        ConsolaVista vista = new ConsolaVista();
        TorneoControlador controlador = new TorneoControlador(torneo, vista);

        controlador.iniciar();
    }
}