package co.edu.uniquindio.reservasuq.modelo.instalaciones;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class Instalaciones {
    String nombre;
    int aforo;
    float costo;
    List<Horario> horarios;

}
