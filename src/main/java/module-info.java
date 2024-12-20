module SistemaDeReservaciones {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    requires org.simplejavamail;
    requires org.simplejavamail.core;
    requires jbcrypt;

    opens co.edu.uniquindio.reservasuq to javafx.fxml;
    exports co.edu.uniquindio.reservasuq;
    opens co.edu.uniquindio.reservasuq.controlador to javafx.fxml;
    exports co.edu.uniquindio.reservasuq.controlador;

    exports co.edu.uniquindio.reservasuq.modelo;

    exports co.edu.uniquindio.reservasuq.modelo.reserva;

    exports co.edu.uniquindio.reservasuq.modelo.enums;

}