package domain;

public abstract class Congedo {
    protected String motivazione;
    protected String stato;
    protected String tipo;
    protected String codice;
    protected String matricola;

    public Congedo(String motivazione, String stato, String tipo, String codice, String matricola) {
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

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
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
