package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Permesso extends Benefit{
    private LocalDate data;
    private LocalTime oraInizio;
    private LocalTime oraFine;
    public Permesso(String motivazione, String stato, String tipo, String codice, String matricola, LocalDate data, LocalTime oraInizio, LocalTime oraFine) {
        super(motivazione, stato, tipo, codice, matricola);
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(LocalTime oraInizio) {
        this.oraInizio = oraInizio;
    }

    public LocalTime getOraFine() {
        return oraFine;
    }

    public void setOraFine(LocalTime oraFine) {
        this.oraFine = oraFine;
    }

    @Override
    public String toString() {
        return
                " data: " + data +
                ", oraInizio: " + oraInizio +
                ", oraFine: " + oraFine +
                ", motivazione: " + motivazione +
                ", stato: " + stato +
                ", tipo: " + tipo +
                ", codice: " + codice +
                ", matricola: " + matricola;
    }
}
