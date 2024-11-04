package co.edu.uniquindio.reservasuq.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter

@AllArgsConstructor

public class Sesion {
    private Persona persona;
    public static Sesion INSTANCIA;

    public static void setINSTANCIA(Sesion INSTANCIA) {
        Sesion.INSTANCIA = INSTANCIA;
    }

    private Sesion() {}

    public static Sesion getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new Sesion();
        }
        return INSTANCIA;
    }

    public void cerrarSesion() {
        persona = null;
    }



    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public static Sesion getINSTANCIA() {
        return INSTANCIA;
    }
}


