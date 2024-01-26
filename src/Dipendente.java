import java.util.*;

public class Dipendente {
    private String nome;
    private String cognome;
    private Date dataDiNascita;
    private String matricola;
    private Ruolo ruolo;

    public Dipendente(String nome, String cognome, Date dataDiNascita, String matricola, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.matricola = matricola;
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public Date getDataDiNascita() {
        return dataDiNascita;
    }
    public String getMatricola() {
        return matricola;
    }
    public Ruolo getRuolo() {
        return ruolo;
    }
    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        return "Dipendente [nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita + ", matricola="
                + matricola + ", ruolo=" + ruolo + "]";
    }
}