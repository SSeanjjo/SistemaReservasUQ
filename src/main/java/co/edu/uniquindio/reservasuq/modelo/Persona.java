package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class Persona {

    private String cedula;
    private String nombre;
    private String correoInstitucional;
    private String contrasena;
    private TipoPersona tipoPersona;//    tipo de usuario (estudiante, docente, administrativo o externos)


    public Persona(String cedula, String nombre, String correoInstitucional, String hashedPassword, TipoPersona tipoPersona) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correoInstitucional = correoInstitucional;
        this.contrasena = hashedPassword;
        this.tipoPersona = tipoPersona;
    }
}
