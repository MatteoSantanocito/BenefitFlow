package domain;

public class Dipendente {
    private String nome;
    private String cognome;
    private String dataDiNascita;
    private String matricola;
    private Ruolo ruolo;


    public Dipendente(String nome, String cognome, String dataDiNascita, String matricola, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.matricola = matricola;
        this.ruolo = ruolo;
    }

    public Dipendente(String nome, String cognome, String dataDiNascita, String matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.matricola = matricola;
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
    public String getDataDiNascita() {
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
        if(this.getRuolo() == null) {
            return "nome: " + nome + ", cognome: " + cognome + ", data di nascita: " + dataDiNascita + ", matricola: "
                    + matricola + "\n    ruolo: non assegnato";
        }
        return "nome: " + nome + ", cognome: " + cognome + ", data di nascita: " + dataDiNascita + ", matricola: "
                + matricola + "\n    ruolo: " + ruolo;
    }
}