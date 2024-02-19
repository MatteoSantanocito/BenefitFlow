package domain;

import java.time.LocalDate;

public class Ferie extends Congedo {

    private LocalDate dataInizio;
    private LocalDate dataFine;

    public Ferie(String motivazione, String stato, String tipo, String codice, String matricola, LocalDate dataInizio, LocalDate dataFine) {
        super(motivazione, stato, tipo, codice, matricola);
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return
                " dataInizio: " + dataInizio +
                ", dataFine: " + dataFine +
                ", motivazione: " + motivazione +
                ", stato: " + stato +
                ", tipo: " + tipo +
                ", codice: " + codice +
                ", matricola: " + matricola;
    }
}
