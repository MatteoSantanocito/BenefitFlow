package domain;

import java.time.LocalDate;

public class BuonoPasto {

    private String codiceBP, stato, matricola;
    private LocalDate dataScadenza;
    private float valore;

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getCodiceBP() {
        return codiceBP;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public float getValore() {
        return valore;
    }

    public BuonoPasto(String codiceBP, String stato, String matricola, LocalDate dataScadenza, float valore) {
        this.codiceBP = codiceBP;
        this.stato = stato;
        this.matricola = matricola;
        this.dataScadenza = dataScadenza;
        this.valore = valore;
    }

    @Override
    public String toString() {
        return
                "codiceBP: " + codiceBP +
                ", stato: " + stato +
                ", matricola: " + matricola +
                ", dataScadenza: " + dataScadenza +
                ", valore: " + valore;
    }
}
