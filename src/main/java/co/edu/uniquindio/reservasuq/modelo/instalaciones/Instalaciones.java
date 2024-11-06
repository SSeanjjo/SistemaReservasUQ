package co.edu.uniquindio.reservasuq.modelo.instalaciones;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class Instalaciones {
    private String id;
    private String nombre;
    private int aforo;
    private float costo;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
//    private List<Horario> horarios;

    public Instalaciones(String id, String nombre, int aforo, float costo, LocalDateTime horaInicio, LocalDateTime horaFin) {
        this.id = id.toString();
        this.nombre = nombre;
        this.aforo = aforo;
        this.costo = costo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    /*public Instalaciones(String id, String nombre, int aforo, float costo, LocalDateTime horaInicio, LocalDateTime horaFin) {
        this.id = String.valueOf(id);
        this.nombre = nombre;
        this.aforo = aforo;
        this.costo = costo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;

    }*/

//    public Instalaciones withId(Integer id) {
//        this.id = String.valueOf(id); // Convert Integer to String
//        return this;
//    }



}
