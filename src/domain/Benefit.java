package domain;

public abstract class Benefit {
    protected String motivazione;
    protected String stato;
    protected String tipo;
    protected int codice;
    protected String matricola;

    public Benefit(String motivazione, String stato, String tipo, int codice, String matricola) {
        this.motivazione = motivazione;
        this.stato = stato;
        this.tipo = tipo;
        this.codice = codice;
        this.matricola = matricola;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    @Override
    public String toString() {
        return
                "motivazione: " + motivazione +
                ", stato: " + stato +
                ", tipo: " + tipo +
                ", codice=: " + codice +
                ", matricola: " + matricola;
    }
}
