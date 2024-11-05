package co.edu.uniquindio.reservasuq.servicio;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Persona;
import co.edu.uniquindio.reservasuq.modelo.reserva.Reserva;
import co.edu.uniquindio.reservasuq.modelo.enums.TipoPersona;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ServiciosReservasUQ {

    Persona login(String correo, String contrasena) throws Exception;

    void registrarPersona(String cedula, String nombre, String correoInstitucional, String contrasena,  TipoPersona tipoPersona) throws Exception;

    void crearInstalacion(String id, String nombre, int aforo, float costo, LocalDateTime horaInicio, java.time.LocalDateTime horaFin);

    Reserva crearReserva(String tipoInstalacion, String idInstalacion, String cedula,  LocalDate diaReserva, String horaReserva) throws Exception;

    Optional<Persona> obtenerPersona(String cedula);

    List<Reserva> listarTodasReservas();

    List<Reserva> listarReservasPorPersona();

    List<Reserva> listarReservasPorPersona(String cedulaPersona);
}

