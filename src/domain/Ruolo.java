package domain;

public class Ruolo {
    private String codiceRuolo;
    private String nome;
    private int livello;
    
    public Ruolo(String codiceRuolo, String nome, int livello) {
        this.codiceRuolo = codiceRuolo;
        this.nome = nome;
        this.livello = livello;
    }

    public String getCodiceRuolo() {
        return codiceRuolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLivello() {
        return livello;
    }

    public void setLivello(int livello) {
        this.livello = livello;
    }

    @Override
    public String toString() {
        return "[codiceRuolo: " + codiceRuolo + ", nome: " + nome + ", livello: " + livello + "]";
    }
}
