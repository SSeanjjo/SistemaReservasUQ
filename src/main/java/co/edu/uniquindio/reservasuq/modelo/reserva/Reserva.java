package co.edu.uniquindio.reservasuq.modelo.reserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class Reserva {
    private  String idInstalacion;
    private  String cedula;
    private  LocalDate diaReserva;
    private  String horaReserva;

}
