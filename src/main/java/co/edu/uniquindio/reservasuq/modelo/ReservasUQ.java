package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.modelo.instalaciones.Instalaciones;
import co.edu.uniquindio.reservasuq.modelo.reserva.Reserva;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservasUQ implements ServiciosReservasUQ {
    public static ReservasUQ INSTANCIA;
    private List<Persona> listaPersonas;
    private List<Reserva> listaReservas;
    private List<Instalaciones> listaInstalaciones;

    private ReservasUQ() {
        listaPersonas = new ArrayList<>();
        listaReservas = new ArrayList<>();
        listaInstalaciones = new ArrayList<>();
    }

    public static ReservasUQ getInstance(){
        if(INSTANCIA == null){
            INSTANCIA = new ReservasUQ();
        }
        return INSTANCIA;
    }

    @Override
    public void registrarPersona(String cedula, String nombre, TipoPersona tipoPersona, String correoInstitucional, String password) throws Exception {
        String ValidationMessage = "";

        if (cedula == null || cedula.isEmpty()) {
            ValidationMessage += "La cedula no puede ser nula o vacia\n";
        }
        if (nombre == null || nombre.isEmpty()) {
            ValidationMessage += "El nombre no puede ser nulo o vacio\n";
        }
        if (correoInstitucional == null || correoInstitucional.isEmpty()) {
            ValidationMessage += "El correo institucional no puede ser nulo o vacio\n";
        }
        if (password == null || password.isEmpty()) {
            ValidationMessage += "La contraseña no puede ser nula o vacia\n";
        }
        if (tipoPersona == null) {
            ValidationMessage += "Debe seleccionar un tipo de Cargo\n";
        }
        if (!ValidationMessage.isEmpty()) {
            throw new Exception(ValidationMessage);
        }

        if (obtenerPersona(cedula) != null) {
            throw new Exception("Ya existe una persona con la cedula ingresada");
        }
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
        Persona loginUsuario = null;

        if (correo == null || correo.isEmpty()) {
            ValidationMessage += "El correo no puede ser nulo o vacio\n";
        }
        if (contrasena == null || contrasena.isEmpty()) {
            ValidationMessage += "La contraseña no puede ser nula o vacia\n";
        }
        if (!ValidationMessage.isEmpty()) {
            throw new Exception(ValidationMessage);
        }

        for (Persona usuarioRegistrado : listaPersonas) {
            if (usuarioRegistrado.getCorreoInstitucional().equals(correo)) {
                if (usuarioRegistrado.getPassword().equals(contrasena)) {
                    loginUsuario = usuarioRegistrado;
                } else {
                    throw new Exception("La contraseña es incorrecta");
                }
            } else {
                throw new Exception("El correo no esta registrado");
            }
        }
        return loginUsuario;
    }


    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {
        String ValidationMessage = "";
        if (nombre == null || nombre.isEmpty()) {
            ValidationMessage += "El nombre de la instalacion no puede ser nulo o vacio\n";
        }
        if (aforo <= 0) {
            ValidationMessage += "El aforo de la instalacion no puede ser menor o igual a 0\n";
        }
        if (costo <= 0) {
            ValidationMessage += "El costo de la instalacion no puede ser menor o igual a 0\n";
        }
        if (horarios == null || horarios.isEmpty()) {
            ValidationMessage += "Debe ingresar al menos un horario\n";
        }

        Instalaciones instalacion = new Instalaciones(nombre, aforo, costo, horarios);
        listaInstalaciones.add(instalacion);


    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedula, LocalDate diaReserva, String horaReserva) throws Exception {
        String ValidationMessage = "";
        if (idInstalacion == null || idInstalacion.isEmpty()) {
            ValidationMessage += "El id de la instalacion no puede ser nulo o vacio\n";
        }
        if (cedula == null || cedula.isEmpty()) {
            ValidationMessage += "La cedula de la persona no puede ser nula o vacia\n";
        }
        if (diaReserva == null) {
            ValidationMessage += "La fecha de la reserva no puede ser nula\n";
        }
        if (horaReserva == null || horaReserva.isEmpty()) {
            ValidationMessage += "La hora de la reserva no puede ser nula o vacia\n";
        }
        if (!ValidationMessage.isEmpty()) {
            throw new Exception(ValidationMessage);
        }
        if (obtenerPersona(cedula) != null) {
            ValidationMessage += ("La persona no esta registrada");
        }

        Reserva reserva = Reserva.builder()
                .idInstalacion(idInstalacion)
                .cedula(cedula)
                .diaReserva(diaReserva)
                .horaReserva(horaReserva)
                .build();

        listaReservas.add(reserva);

        return reserva;
    }

    @Override
    public Persona obtenerPersona(String cedula) {
        for (Persona persona : listaPersonas) {
            if (persona.getCedula().equals(cedula)) {
                return persona;
            }
        }
        return null;
    }


    @Override
    public List<Reserva> listarTodasReservas() {
        return List.of();
    }

    @Override
    public List<Reserva> listarReservasPorPersona() {
        return List.of();
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return List.of();
    }

}
