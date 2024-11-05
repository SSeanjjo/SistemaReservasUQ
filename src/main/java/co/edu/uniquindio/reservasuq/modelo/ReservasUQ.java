package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.modelo.instalaciones.Instalaciones;
import co.edu.uniquindio.reservasuq.modelo.reserva.Reserva;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Getter
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

    public static ReservasUQ getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new ReservasUQ();
        }
        return INSTANCIA;
    }

    @Override
    public void registrarPersona(String cedula, String nombre, String correoInstitucional, String contrasena, TipoPersona tipoPersona) throws Exception {
        // Perform validations...
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
        if (contrasena == null || contrasena.isEmpty()) {
            ValidationMessage += "La contraseña no puede ser nula o vacia\n";
        }
        if (tipoPersona == null) {
            ValidationMessage += "Debe seleccionar un tipo de Cargo\n";
        }
        if (!ValidationMessage.isEmpty()) {
            throw new Exception(ValidationMessage);
        }

//        if (obtenerPersona(cedula) != null) {
//            throw new Exception("Ya existe una persona con la cedula ingresada");
//        }
        if (listaPersonas.stream().anyMatch(persona -> persona.getCedula().equals(cedula))) {
            throw new Exception("La cédula ya está registrada");
        }
        // Password hashing
        String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());
        Persona nuevaPersona = new Persona(cedula, nombre, correoInstitucional, hashedPassword, tipoPersona);
        listaPersonas.add(nuevaPersona);

//    String hashedPassword = BCrypt.hashpw(hashedPassword, BCrypt.gensalt());
//        Persona persona = Persona.builder()
//                .cedula(getPersona().getCedula())
//                .nombre(getPersona().getNombre())
//                .correoInstitucional(getPersona().getCorreoInstitucional())
//                .contrasena(hashedPassword)
//                .tipoPersona(getPersona().getTipoPersona())
//                .build();
//        listaPersonas.add(persona);
    }

    @Override
    public Persona login(String correo, String contrasena) throws Exception {
        if (correo == null || correo.isEmpty()) {
            throw new Exception("El correo no puede ser nulo o vacío");
        }
        if (contrasena == null || contrasena.isEmpty()) {
            throw new Exception("La contraseña no puede ser nula o vacía");
        }

        // Search for user by email
        Persona usuarioRegistrado = listaPersonas.stream()
                .filter(persona -> persona.getCorreoInstitucional().equals(correo))
                .findFirst()
                .orElse(null);

        if (usuarioRegistrado == null) {
            throw new Exception("El correo no está registrado");
        }

        // Verify password using BCrypt
        if (!BCrypt.checkpw(contrasena, usuarioRegistrado.getContrasena())) {
            throw new Exception("La contraseña es incorrecta");
        }

        return usuarioRegistrado;
    }


/*    @Override
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

//        Persona loginUsuario = null;
        for (Persona usuarioRegistrado : listaPersonas) {
            if (usuarioRegistrado.getCorreoInstitucional().equals(correo)) {
                if (BCrypt.checkpw(contrasena, usuarioRegistrado.getPassword())) { // Use BCrypt here
                    loginUsuario = usuarioRegistrado;
                } else {
                    throw new Exception("La contraseña es incorrecta");
                }
            }
        }
        if (loginUsuario == null) {
            throw new Exception("El correo no está registrado");
        }
        return loginUsuario;
    }*/


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
    public Optional<Persona> obtenerPersona(String cedula) {
        return listaPersonas.stream()
                .filter(persona -> persona.getCedula().equals(cedula))
                .findFirst();
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
}

    //    }
//        return null;
//        }
//            }
//                return persona;
//            if (persona.getCedula().equals(cedula)) {
//        for (Persona persona : listaPersonas) {
//    public Optional<Persona> obtenerPersona(String cedula) {
//    @Override
    /*@Override
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
                if (BCrypt.checkpw(contrasena, usuarioRegistrado.getPassword())) {
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

*/
