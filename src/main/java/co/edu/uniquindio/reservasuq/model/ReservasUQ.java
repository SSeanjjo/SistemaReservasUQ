package co.edu.uniquindio.reservasuq.model;

import co.edu.uniquindio.reservasuq.model.reserva.Reserva;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;

import java.time.LocalDate;
import java.util.List;

public class ReservasUQ implements ServiciosReservasUQ {


    @Override
    public Persona login(String correo, String contrasena) throws Exception {
        return null;
    }

    @Override
    public void registrarPersona(String cedula, String nombre, TipoPersona tipoPersona, String email, String password) throws Exception {

    }

    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {

    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        return null;
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return List.of();
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return List.of();
    }
}
