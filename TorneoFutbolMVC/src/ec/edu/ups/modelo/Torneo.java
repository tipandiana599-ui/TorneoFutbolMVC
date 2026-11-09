package ec.edu.ups.modelo;

import java.io.*;
import java.util.*;

public class Torneo implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Equipo> equipos;
    private List<Partido> partidosOctavos;
    private List<Partido> partidosCuartos;
    private List<Partido> partidosSemifinal;
    private List<Partido> partidosFinal;

    public Torneo() {
        this.equipos = new ArrayList<>();
        this.partidosOctavos = new ArrayList<>();
        this.partidosCuartos = new ArrayList<>();
        this.partidosSemifinal = new ArrayList<>();
        this.partidosFinal = new ArrayList<>();
    }

    public void registrarEquiposIniciales() {
        equipos.clear();
        for (int i = 1; i <= 16; i++) {
            equipos.add(new Equipo("Equipo " + i));
        }
    }

    // Algoritmo de sorteo recursivo
    public void sortearTorneoRecursivo(List<Equipo> listaEquipos, String etapa) throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        int n = listaEquipos.size();
        
        if (n != 16 && n != 8 && n != 4 && n != 2) {
            throw new NumeroEquiposInvalidoException("El número de equipos (" + n + ") no es una potencia válida de 2 (16, 8, 4, 2).");
        }

        if (n == 0) {
            throw new SorteoInvalidoException("Error: La lista de equipos está vacía en la etapa " + etapa);
        }

        List<Equipo> copia = new ArrayList<>(listaEquipos);
        Collections.shuffle(copia);
        List<Partido> partidosEtapa = new ArrayList<>();

        for (int i = 0; i < copia.size(); i += 2) {
            Equipo local = copia.get(i);
            Equipo visitante = copia.get(i + 1);
            if (local.equals(visitante)) {
                throw new SorteoInvalidoException("Conflicto: Un equipo no puede enfrentarse a sí mismo.");
            }
            partidosEtapa.add(new Partido(local, visitante, etapa));
        }

        List<Equipo> ganadores = new ArrayList<>();
        for (Partido p : partidosEtapa) {
            ganadores.add(p.getEquipoLocal()); // Pasa a la siguiente fase
        }

        if (etapa.equals("Octavos")) {
            this.partidosOctavos = partidosEtapa;
            sortearTorneoRecursivo(ganadores, "Cuartos");
        } else if (etapa.equals("Cuartos")) {
            this.partidosCuartos = partidosEtapa;
            sortearTorneoRecursivo(ganadores, "Semifinal");
        } else if (etapa.equals("Semifinal")) {
            this.partidosSemifinal = partidosEtapa;
            sortearTorneoRecursivo(ganadores, "Final");
        } else if (etapa.equals("Final")) {
            this.partidosFinal = partidosEtapa;
        }
    }

    // Métodos de Serialización
    public void guardarTorneo(String rutaArchivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(this);
        }
    }

    public static Torneo cargarTorneo(String rutaArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (Torneo) ois.readObject();
        }
    }

    public List<Partido> getPartidosOctavos() { return partidosOctavos; }
    public List<Partido> getPartidosCuartos() { return partidosCuartos; }
    public List<Partido> getPartidosSemifinal() { return partidosSemifinal; }
    public List<Partido> getPartidosFinal() { return partidosFinal; }
}