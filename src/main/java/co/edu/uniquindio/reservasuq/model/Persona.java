package co.edu.uniquindio.reservasuq.model;

import lombok.Data;

@Data
public class Persona {

    public String cédula, nombre, correoInstitucional, contraseña;
    public TipoPersona tipoPersona;
//    tipo de usuario (estudiante, docente, administrativo o externos)

}
