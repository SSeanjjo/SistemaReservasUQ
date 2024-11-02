package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.reserva.Reserva;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;

import java.time.LocalDate;
import java.util.List;

public class ReservasUQ implements ServiciosReservasUQ{
    private List<Persona> listaPersonas;


    @Override
    public void registrarPersona(String cedula, String nombre, TipoPersona tipoPersona, String correoInstitucional, String password) throws Exception {
        String ValidationMessage = "";

        if(cedula == null || cedula.isEmpty()){ValidationMessage += "La cedula no puede ser nula o vacia\n";}
        if(nombre == null || nombre.isEmpty()){ValidationMessage += "El nombre no puede ser nulo o vacio\n";}
        if(correoInstitucional == null || correoInstitucional.isEmpty()){ValidationMessage += "El correo institucional no puede ser nulo o vacio\n";}
        if(password == null || password.isEmpty()){ValidationMessage += "La contraseña no puede ser nula o vacia\n";}
        if(tipoPersona == null){ValidationMessage += "Debe seleccionar un tipo de Cargo\n";}
        if(!ValidationMessage.isEmpty()){throw new Exception(ValidationMessage);}

        if(obtenerPersona(cedula)!= null){throw new Exception("Ya existe una persona con la cedula ingresada");}

//        TipoPersona tipoCargoPersona = switch (tipoPersona){
//            case ESTUDIANTE -> new
//        }

        Persona persona = Persona.builder()
                .cedula(cedula)
                .nombre(nombre)
                .correoInstitucional(correoInstitucional)
                .password(password)
                .tipoPersona(tipoPersona)
                .build();
        listaPersonas.add(persona);
    }

    @Override
    public Persona login(String correo, String contrasena) throws Exception {
        String ValidationMessage = "";
        if (correo == null || correo.isEmpty()) {ValidationMessage += "El correo no puede ser nulo o vacio\n";}
        if (contrasena == null || contrasena.isEmpty()) {ValidationMessage += "La contraseña no puede ser nula o vacia\n";}
        if (!ValidationMessage.isEmpty()) {throw new Exception(ValidationMessage);}

        for(Persona UsuarioRegistrado: listaPersonas){
                if(UsuarioRegistrado.getCorreoInstitucional().equals(correo){
                    if(UsuarioRegistrado.getPassword().equals(contrasena)){
                        return UsuarioRegistrado;
                    }else{
                        throw new Exception("La contraseña es incorrecta");
                    }
                throw new Exception("El correo no esta registrado");
                }
            }
    }



    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {

    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        return null;
    }

    @Override
    public Persona obtenerPersona(String cedula) {
        for(Persona persona: listaPersonas){
            if(persona.getCedula().equals(cedula)){
                return persona;
        }
    }
        return null;


    @Override
    public List<Reserva> listarTodasReservas() {
        return List.of();
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return List.of();
    }
}
