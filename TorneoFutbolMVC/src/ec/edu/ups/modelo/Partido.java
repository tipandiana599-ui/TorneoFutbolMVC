package ec.edu.ups.modelo;

import java.io.Serializable;

public class Partido implements Serializable {
    private static final long serialVersionUID = 1L;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private String etapa; // Octavos, Cuartos, Semifinal, Final

    public Partido(Equipo equipoLocal, Equipo equipoVisitante, String etapa) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.etapa = etapa;
    }

    public Equipo getEquipoLocal() { return equipoLocal; }
    public Equipo getEquipoVisitante() { return equipoVisitante; }
    public String getEtapa() { return etapa; }

    @Override
    public String toString() {
        return "[" + etapa + "] " + equipoLocal.getNombre() + " vs " + equipoVisitante.getNombre();
    }
}