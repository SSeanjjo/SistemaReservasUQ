package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class Persona {

    private String cedula;
    private String nombre;
    private String correoInstitucional;
    private String password;
    private TipoPersona tipoPersona;//    tipo de usuario (estudiante, docente, administrativo o externos)


}
