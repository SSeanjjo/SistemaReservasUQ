package co.edu.uniquindio.reservasuq.modelo.reserva;

import co.edu.uniquindio.reservasuq.modelo.instalaciones.Instalaciones;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class Reserva {
    private String idInstalacion;
    private String cedulaReservante;
    private LocalDate diaReserva;
    private String horaReserva;
    private String cedula;
    private double costo;

    public String getNombreInstalacion(List<Instalaciones> listaInstalaciones) {
        for (Instalaciones instalacion : listaInstalaciones) {
            if (instalacion.getId().equals(this.idInstalacion)) {
                return instalacion.getNombre();
            }
        }
        return "Instalacion no encontrada";
    }
}
