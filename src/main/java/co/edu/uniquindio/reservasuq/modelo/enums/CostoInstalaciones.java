package co.edu.uniquindio.reservasuq.modelo.enums;

public enum CostoInstalaciones {
    PISTA_ATLETISMO(8000),
    PISCINA(16000),
    GIMNASIO(8000),
    CANCHA_DE_FÚTBOL(120000),
    CANCHA_DE_BALONCESTO(80000),
    AULAS_DE_ESTUDIO_GRUPAL(30000),
    SALONES_DE_EVENTOS(250000);


    private final int costo;

    CostoInstalaciones(int costo) {
        this.costo = costo;
    }

    public int getCosto() {
        return costo;
    }
}
