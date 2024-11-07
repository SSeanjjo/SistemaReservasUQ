package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import co.edu.uniquindio.reservasuq.modelo.instalaciones.Instalaciones;
import co.edu.uniquindio.reservasuq.modelo.reserva.Reserva;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public static synchronized ReservasUQ getInstance() {
        if (INSTANCIA == null) {
            INSTANCIA = new ReservasUQ();
        }
        return INSTANCIA;
    }

    @Override
    public void registrarPersona(String cedula, String nombre, String correoInstitucional, String contrasena, TipoPersona tipoPersona) throws Exception {
        // Perform validations...
        String ValidationMessage = "";

        if (cedula == null || cedula.isEmpty()) {ValidationMessage += "La cedula no puede ser nula o vacia\n";}
        if (nombre == null || nombre.isEmpty()) {ValidationMessage += "El nombre no puede ser nulo o vacio\n";}
        if (correoInstitucional == null || correoInstitucional.isEmpty()) {ValidationMessage += "El correo institucional no puede ser nulo o vacio\n";}
        if (contrasena == null || contrasena.isEmpty()) {ValidationMessage += "La contraseña no puede ser nula o vacia\n";}
        if (tipoPersona == null) {ValidationMessage += "Debe seleccionar un tipo de Cargo\n";}
        if (!ValidationMessage.isEmpty()) {throw new Exception(ValidationMessage);}

        if (listaPersonas.stream().anyMatch(persona -> persona.getCedula().equals(cedula))) {throw new Exception("La cédula ya está registrada");}
        // Password hashing
        String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());
        Persona nuevaPersona = new Persona(cedula, nombre, correoInstitucional, hashedPassword, tipoPersona);
        listaPersonas.add(nuevaPersona);
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
        /*Persona usuarioRegistrado = listaPersonas.stream()
                .filter(persona -> persona.getCorreoInstitucional().equals(correo))
                .findFirst()
                .orElse(null);

        if (usuarioRegistrado == null) {
            throw new Exception("El correo no está registrado");
        }

        // Verify password using BCrypt
        if (!BCrypt.checkpw(contrasena, usuarioRegistrado.getContrasena())) {
            throw new Exception("La contraseña es incorrecta");
        }*/
        Persona usuarioRegistrado = listaPersonas.stream()
                .filter(persona -> persona.getCorreoInstitucional().equals(correo))
                .findFirst()
                .orElseThrow(() -> new Exception("El correo no está registrado"));

        if (!BCrypt.checkpw(contrasena, usuarioRegistrado.getContrasena())) {
            throw new Exception("La contraseña es incorrecta");
        }

        return usuarioRegistrado;
    }

    @Override
    public Reserva crearReserva(String tipoInstalacion, String idInstalacion, String cedula, LocalDate diaReserva, String horaReserva) throws Exception {
        // Validate required parameters
        if (idInstalacion == null || idInstalacion.isEmpty() ||
                cedula == null || cedula.isEmpty() ||
                diaReserva == null ||
                horaReserva == null || horaReserva.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios.");
        }

        // Convert date and time into LocalDateTime
        LocalDateTime fechaReserva = LocalDateTime.of(diaReserva, LocalTime.parse(horaReserva));

        // Find the installation by ID
        Instalaciones instalacion = listaInstalaciones.stream()
                .filter(i -> i.getId().equals(idInstalacion))
                .findFirst()
                .orElseThrow(() -> new Exception("No hay ninguna instalación asociada al ID ingresado."));

        // Check if the installation is available
        if (!isAvailable(idInstalacion, diaReserva, horaReserva)) {
            throw new Exception("El horario solicitado ya está reservado.");
        }

        // Check if reservation time falls within installation operating hours
//        if (fechaReserva.isBefore(instalacion.getHoraInicio()) || fechaReserva.isAfter(instalacion.getHoraFin().minusMinutes(1))) {
//            throw new Exception("La reserva debe estar dentro del horario de la instalación.");
//        }

        // Get the user and calculate the cost based on the installation and user type
        Persona persona = obtenerPersona(cedula).orElseThrow(() -> new Exception("Persona no encontrada"));
        double costoReserva = calcularCosto(tipoInstalacion, persona.getTipoPersona().name());

        // Create and add the reservation to the list, now with the calculated cost
        Reserva reserva = Reserva.builder()
                .idInstalacion(idInstalacion)
                .cedula(cedula)
                .diaReserva(diaReserva)
                .horaReserva(horaReserva)
                .costo(costoReserva)  // Ensure cost is included in the reservation
                .build();
        listaReservas.add(reserva);

        return reserva;
    }

    /*
    @Override
    public Reserva crearReserva(String tipoInstalacion, String idInstalacion, String cedula, LocalDate diaReserva, String horaReserva) throws Exception {
        // Validate required parameters
        if (idInstalacion == null || idInstalacion.isEmpty() ||
                cedula == null || cedula.isEmpty() ||
                diaReserva == null ||
                horaReserva == null || horaReserva.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios.");
        }

        // Convert date and time into LocalDateTime
        LocalDateTime fechaReserva = LocalDateTime.of(diaReserva, LocalTime.parse(horaReserva));

        // Find the installation by ID
        Instalaciones instalacion = listaInstalaciones.stream()
                .filter(i -> i.getId().equals(idInstalacion))
                .findFirst()
                .orElseThrow(() -> new Exception("No hay ninguna instalación asociada al ID ingresado."));

        // Check if the installation is available
        if (!isAvailable(idInstalacion, diaReserva, horaReserva)) {
            throw new Exception("El horario solicitado ya está reservado.");
        }

        // Check if reservation time falls within installation operating hours
        if (fechaReserva.isBefore(instalacion.getHoraInicio()) || fechaReserva.isAfter(instalacion.getHoraFin().minusMinutes(1))) {
            throw new Exception("La reserva debe estar dentro del horario de la instalación.");
        }


        /*if (fechaReserva.isBefore(instalacion.getHoraInicio()) || fechaReserva.isAfter(instalacion.getHoraFin())) {
            throw new Exception("La reserva debe estar dentro del horario de la instalación.");
        }

        // Get the user and calculate the cost based on the installation and user type
        Persona persona = obtenerPersona(cedula).orElseThrow(() -> new Exception("Persona no encontrada"));
        double costoReserva = calcularCosto(tipoInstalacion, persona.getTipoPersona().name());

        // Create and add the reservation to the list, now with the calculated cost
        Reserva reserva = Reserva.builder()
                .idInstalacion(idInstalacion)
                .cedula(cedula)
                .diaReserva(diaReserva)
                .horaReserva(horaReserva)
                .costo(costoReserva)  // Ensure cost is included in the reservation
                .build();
        listaReservas.add(reserva);

        return reserva;
    }*/

    public double calcularCosto(String tipoInstalacion, String tipoPersona) {
        // Find the selected installation
        Instalaciones instalacion = listaInstalaciones.stream()
                .filter(i -> i.getNombre().equals(tipoInstalacion))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Instalación no encontrada"));

        // Base cost of the installation
        double baseCost = instalacion.getCosto();

        // Apply discount based on person type
        switch (tipoPersona) {
            case "ESTUDIANTE":
                return baseCost * 1.0;  // No discount for students
            case "PROFESOR":
                return baseCost * 0.5;  // 50% discount for professors
            case "VISITANTE":
                return baseCost;  // No discount for visitors
            default:
                return baseCost * 1.1;  // 10% surcharge for unrecognized types
        }
    }

    /*
    public double calcularCosto(String tipoInstalacion, String tipoPersona) {
        // Base cost for the installation type
        double baseCost = listaInstalaciones.stream()
                .filter(instalacion -> instalacion.getNombre().equals(tipoInstalacion))
                .findFirst()
                .map(Instalaciones::getCosto)
                .orElse(10000.0f); // Default base cost if installation type not found

        // Apply discount or surcharge based on user type
        double tarifa;
        switch (tipoPersona) {
            case "ESTUDIANTE":
                tarifa = baseCost * 1; // 20% discount for students
                break;
            case "PROFESOR":
                tarifa = baseCost * 0.5; // 10% discount for professors
                break;
            case "VISITANTE":
                tarifa = baseCost; // No discount for visitors
                break;
            default:
                tarifa = baseCost * 1.1; // 10% surcharge for others
                break;
        }
        return tarifa;
    }*/
/*
    @Override
    public Reserva crearReserva(String tipoInstalacion, String idInstalacion, String cedula, LocalDate diaReserva, String horaReserva, double costo) throws Exception {
        // Validación de parámetros
        if (idInstalacion == null || idInstalacion.isEmpty() ||
                cedula == null || cedula.isEmpty() ||
                diaReserva == null ||
                horaReserva == null || horaReserva.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios.");
        }

        LocalDateTime fechaReserva = LocalDateTime.of(diaReserva, LocalTime.parse(horaReserva));

        Instalaciones instalacion = listaInstalaciones.stream()
                .filter(i -> i.getId().equals(idInstalacion))
                .findFirst()
                .orElseThrow(() -> new Exception("No hay ninguna instalación asociada al ID ingresado."));


        if (!isAvailable(idInstalacion, diaReserva, horaReserva)) {
            throw new Exception("El horario solicitado ya está reservado.");
        }

        boolean instalacionEncontrada = false;

        for (Instalaciones instalaciones : listaInstalaciones) {
            if (instalaciones.getId().equals(idInstalacion)) {
                instalacionEncontrada = true;
                LocalDateTime horaInicio = instalaciones.getHoraInicio();
                LocalDateTime horaFin = instalaciones.getHoraFin();

                // Validate that the reservation time falls within installation hours
//                if (fechaReserva.isBefore(horaInicio) || fechaReserva.isAfter(horaFin)) {
//                    throw new Exception("La reserva debe estar dentro del horario de la instalación.");
//                }
//                if (fechaReserva.isBefore(horaInicio) || fechaReserva.isAfter(horaFin)) {
//                    throw new Exception("La reserva debe estar dentro del horario de la instalación.");
//                }

                // Additional checks for availability and cost calculation
                if (!isAvailable(idInstalacion, diaReserva, horaReserva)) {
                    throw new Exception("El horario solicitado ya está reservado.");
                }

                Persona persona = obtenerPersona(cedula).orElseThrow(() -> new Exception("Persona no encontrada"));
                double costoReserva = calcularCosto(tipoInstalacion, persona.getTipoPersona().name(), cedula);

                Reserva reserva = Reserva.builder()
                        .idInstalacion(idInstalacion)
                        .cedula(cedula)
                        .diaReserva(diaReserva)
                        .horaReserva(horaReserva)
                        .costo(costoReserva)
                        .build();
                listaReservas.add(reserva);
                return reserva;
            }
        }
        if (!instalacionEncontrada) {
            throw new Exception("No hay ninguna instalación asociada al ID ingresado.");
        }
        return null;
    }

    public double calcularCosto(String tipoInstalacion, String cedula) throws Exception {
        Persona persona = obtenerPersona(cedula)
                .orElseThrow(() -> new Exception("Persona no encontrada"));
        Instalaciones instalacion = listaInstalaciones.stream()
                .filter(i -> i.getNombre().equals(tipoInstalacion))
                .findFirst()
                .orElseThrow(() -> new Exception("Instalación no encontrada"));

        double baseCost = instalacion.getCosto();
        double tarifa;
        switch (persona.getTipoPersona().name()) {
            case "ESTUDIANTE":
                tarifa = baseCost * 1.0;
                break;
            case "PROFESOR":
                tarifa = baseCost * 0.5;
                break;
            case "VISITANTE":
                tarifa = baseCost;
                break;
            default:
                tarifa = baseCost * 1.1; // Default for other types
        }
        return tarifa;
    }
*/


/*
    public double calcularCosto(String tipoInstalacion, String tipoPersona, String cedula){
        double tarifa = 0;
        for(Persona persona : listaPersonas) {
            if (persona.getCedula().equals(cedula)) {
                for (Instalaciones instalaciones : listaInstalaciones) {
                    if (instalaciones.getNombre().equals(tipoInstalacion)) {
                        tarifa = instalaciones.getCosto();
                        double baseCost = 0;
                        switch (instalaciones.getNombre()) {
                            case "Pista de Atletismo", "Piscina", "Gimnasio", "Cancha de Fútbol, Cancha de Balocesto, Aulas de estudio grupal, Salas de estudio individual":
                                if (instalaciones.getNombre().equals(tipoInstalacion)) {
                                    baseCost = instalaciones.getCosto();
                                }
                                break;
                            default:
                                baseCost = 10.000;
                        }
                        switch (persona.getTipoPersona().name()) {
                            case "ESTUDIANTE":
                                tarifa = baseCost * 1;
                                break;
                            case "PROFESOR":
                                tarifa = baseCost * 0.5;
                                break;
                            case "VISITANTE":
                                tarifa = baseCost;
                                break;
                            default:
                                tarifa = baseCost * 1.1;
                        }

                    }
                }
            }
        }
        return tarifa;
    }*/

    private boolean isAvailable(String idInstalacion, LocalDate diaReserva, String horaReserva) {
        for (Reserva reserva : listaReservas) {
            if (reserva.getIdInstalacion().equals(idInstalacion) &&
                    reserva.getDiaReserva().equals(diaReserva) &&
                    reserva.getHoraReserva().equals(horaReserva)) {
                return false; // Time slot already booked
            }
        }
        return true; // Time slot available
    }

    @Override
    public void crearInstalacion(String id, String nombre, int aforo, float costo, LocalDateTime horaInicio, LocalDateTime horaFin) {
        String validationMessage = "";
        if (nombre == null || nombre.isEmpty()) validationMessage += "El nombre de la instalación no puede ser nulo o vacío\n";
        if (aforo <= 0) validationMessage += "El aforo de la instalación no puede ser menor o igual a 0\n";
        if (costo < 0) validationMessage += "El costo de la instalación no puede ser menor o igual a 0\n";
        if (horaInicio == null) validationMessage += "La hora de inicio no puede ser nula\n";
        if (horaFin == null) validationMessage += "La hora de fin no puede ser nula\n";

        if (!validationMessage.isEmpty()) throw new IllegalArgumentException(validationMessage);

        listaInstalaciones.add(new Instalaciones(id, nombre, aforo, costo, horaInicio, horaFin));
    }

    @Override
    public Optional<Persona> obtenerPersona(String cedula) {
        return listaPersonas.stream()
                .filter(persona -> persona.getCedula().equals(cedula))
                .findFirst();
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return new ArrayList<>(listaReservas); // Return a copy to avoid external modification
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

//    @Override
//    public Reserva crearReserva(String idInstalacion, String cedula, String tipoInstalacion, LocalDate diaReserva, String horaReserva) throws Exception {
//        StringBuilder validationMessage = new StringBuilder();
//
//        if (idInstalacion == null || idInstalacion.isEmpty()) {validationMessage.append("El id de la instalación no puede ser nulo o vacío.\n");}
//        if (cedula == null || cedula.isEmpty()) {validationMessage.append("La cédula no puede ser nula o vacía.\n");}
//        if (diaReserva == null) {validationMessage.append("La fecha de la reserva no puede ser nula.\n");}
//        if (horaReserva == null || horaReserva.isEmpty()) {validationMessage.append("La hora de la reserva no puede ser nula o vacía.\n");}
//        if (obtenerPersona(cedula).isPresent()) {throw new Exception("La cédula ya está registrada");}
//
//        if (validationMessage.length() > 0) {throw new Exception(validationMessage.toString());}
//
//        LocalDateTime fechaReserva = LocalDateTime.of(diaReserva, LocalTime.parse(horaReserva));
//
//        for (Instalaciones instalacion : listaInstalaciones) {
//            if (instalacion.getId().equals(idInstalacion)) {
//                LocalDateTime horaInicio = instalacion.getHoraInicio();
//                LocalDateTime horaFin = instalacion.getHoraFin();
//
//                if (fechaReserva.isBefore(horaInicio) || fechaReserva.isAfter(horaFin)) {throw new Exception("La reserva debe estar dentro del horario de la instalación.");}
//                if (!isAvailable(idInstalacion, diaReserva, horaReserva)) {throw new Exception("El horario solicitado ya está reservado.");}
//
//                Reserva reserva = Reserva.builder()
//                        .idInstalacion(idInstalacion)
//                        .cedula(cedula)
//                        .diaReserva(diaReserva)
//                        .horaReserva(horaReserva)
//                        .build();
//                listaReservas.add(reserva);
//                return reserva;
//            } else if (!instalacion.getId().equals(idInstalacion)){ throw new Exception("No hay ninguna instalación asociada al ID ingresado.");}
//        }
//        return null;
//    }

//        if (!fechaReserva.isBefore(instalacion.getHoraInicio()) || !fechaReserva.isAfter(instalacion.getHoraFin())) {
//            throw new Exception("La reserva debe estar dentro del horario de la instalación.");
//        }

    /*@Override
    public void crearInstalacion(String id, String nombre, int aforo, float costo, LocalDateTime horaInicio, LocalDateTime horaFin) {

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

        if (horaInicio == null || horaInicio.isBefore(LocalDateTime.MIN.plusHours(6))) {
            ValidationMessage += "La hora de inicio no puede ser nula\n";
        }
        if (horaFin == null || horaFin.isAfter(LocalDateTime.MAX.minusHours(20))) {
            ValidationMessage += "La hora de fin no puede ser nula\n";
        }
        else {Instalaciones instalacion = new Instalaciones(id, nombre, aforo, costo, horaInicio, horaFin);
        listaInstalaciones.add(instalacion);}
    }*/