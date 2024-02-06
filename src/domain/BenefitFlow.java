package domain;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class BenefitFlow extends JFrame {

    private static BenefitFlow benefitFlow;
    private Dipendente dipendenteCorrente;
    private Map<String, Ruolo> ruoli;
    private Map<String, Dipendente> elencoDipendenti;
    private Login login;

    private Benefit benefit;
    private Ferie ferie;
    private Permesso permesso;
    private Map<Integer, Ferie> elencoFerie;
    private Map<Integer, Permesso> elencoPermessi;

    public BenefitFlow(){
        this.elencoDipendenti = new HashMap<>();
        this.ruoli = new HashMap<>();
        loadRuoli();

        this.elencoFerie = new HashMap<>();
        this.elencoPermessi = new HashMap<>();
    }

    public void initComponent() {
        login  = new Login(this);

        setLayout(new BorderLayout());
        add(login,BorderLayout.CENTER);

        setVisible(true);
        setTitle("BenefitFlow");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static BenefitFlow getInstance(){
        if(benefitFlow == null){
            benefitFlow = new BenefitFlow();
        }else{
            System.out.println("Istanza già creata");
        }
        return benefitFlow;
    }

    boolean flag = false;
    public void inserisciNuovoDipendente(String nome, String cognome, String dataDiNascita, String matricola, String codiceRuolo){
        Ruolo r = ruoli.get(codiceRuolo);
        Dipendente d = elencoDipendenti.get(matricola);
        if(d == null){
            if(r != null){
                this.dipendenteCorrente = new Dipendente(nome,cognome,dataDiNascita,matricola,r);
                System.out.println("Dipendente inserito correttamente!");
            }else{
                if (codiceRuolo != null) {
                    this.dipendenteCorrente = new Dipendente(nome, cognome, dataDiNascita, matricola);
                    System.out.println("Il ruolo inserito non esiste. Il dipendente è stato comunque inserito senza ruolo.");
                }else{
                    this.dipendenteCorrente = new Dipendente(nome, cognome, dataDiNascita, matricola);
                    System.out.println("Dipendente inserito correttamente senza ruolo!");
                }
            }
        }else{
            flag = true;
            System.out.println("Esiste già un dipendente con matricola " + matricola);
        }
    }

    public void confermaInserimento(){
        if(dipendenteCorrente != null && !flag){
            this.elencoDipendenti.put(dipendenteCorrente.getMatricola(),dipendenteCorrente);
            System.out.println("Lista dipendenti aggiornata con successo!");
        }
        flag = false;
    }

    public List<Dipendente> mostraDipendenti(){
        List<Dipendente> listDipendenti = new ArrayList<>();
        listDipendenti.addAll(elencoDipendenti.values());
        return listDipendenti;
    }

    public void confermaRuolo(String matricola, String codiceRuolo){
        Dipendente d = elencoDipendenti.get(matricola);
        if(d != null){
            Ruolo r = d.getRuolo();
            if(r == null){
                Ruolo ruolo = ruoli.get(codiceRuolo);
                d.setRuolo(ruolo);
                System.out.println("Ruolo assegnato correttamente.");
            } else {
                Ruolo ruolo = ruoli.get(codiceRuolo);
                if (ruolo != null) {
                    d.setRuolo(ruolo);
                    System.out.println("Ruolo modificato correttamente.");
                }else{
                    System.out.println("Il codice ruolo inserito non esiste. Mantengo il precedente ruolo.");
                }
            }
        }
    }

    public void loadRuoli() {
        Ruolo r1 = new Ruolo("ing01", "dataAnalist", 1);
        Ruolo r2 = new Ruolo("ing02", "dataAnalist", 2);
        Ruolo r3 = new Ruolo("ing03", "dataAnalist", 3);
        Ruolo r4 = new Ruolo("inf01", "javaDev", 1);
        Ruolo r5 = new Ruolo("inf02", "javaDev", 2);
        Ruolo r6 = new Ruolo("inf03", "javaDev", 3);
        Ruolo r7 = new Ruolo("ing04", "softwareDev", 1);
        Ruolo r8 = new Ruolo("ing05", "softwareDev", 2);
        Ruolo r9 = new Ruolo("ing06", "softwareDev", 3);
        this.ruoli.put("ing01", r1);
        this.ruoli.put("ing02", r2);
        this.ruoli.put("ing03", r3);
        this.ruoli.put("inf01", r4);
        this.ruoli.put("inf02", r5);
        this.ruoli.put("inf03", r6);
        this.ruoli.put("ing04", r7);
        this.ruoli.put("ing05", r8);
        this.ruoli.put("ing06", r9);
        System.out.println("Caricamento Ruoli ultimato");
    }


    int counter = 0;
    public void inserisciFerie(String matricola, String motivazione, String dataInizio, String dataFine){
        counter += 1;
        Dipendente d = elencoDipendenti.get(matricola);
        String stato = "in elaborazione";
        int codice = counter;
        String tipo = "F";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate dataInizioFormatted = LocalDate.parse(dataInizio, formatter);
            LocalDate dataFineFormatted = LocalDate.parse(dataFine, formatter);
            if(d != null){
                this.ferie = new Ferie(motivazione, stato, tipo, codice, matricola, dataInizioFormatted, dataFineFormatted);
            }
        } catch (Exception e) {
            System.err.println("Inserimento ferie fallito. Formato data non valido.");
        }
    }

    public void confermaFerie(){
        if(this.ferie != null){
            Ferie f = this.ferie;
            int codice = f.getCodice();
            this.elencoFerie.put(codice,f);
            System.out.println("Ferie inserita correttamente: \n" + f);
        }
    }

    public void inserisciPermesso(String matricola, String motivazione, String data, String oraInizio, String oraFine){
        counter += 1;
        Dipendente d = elencoDipendenti.get(matricola);
        String stato = "in elaborazione";
        int codice = counter;
        String tipo = "P";
        DateTimeFormatter formatterTimeI = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterTimeF = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalTime oraInizioFormatted = LocalTime.parse(oraInizio, formatterTimeI);
            LocalTime oraFineFormatted = LocalTime.parse(oraFine, formatterTimeF);
            LocalDate dataFormatted = LocalDate.parse(data, formatterData);
            if(d != null){
                this.permesso = new Permesso(motivazione, stato, tipo, codice, matricola, dataFormatted, oraInizioFormatted, oraFineFormatted);
            }
        } catch (Exception e) {
            System.err.println("Inserimento permesso fallito. Formato data non valido.");
        }
    }

    public void confermaPermesso(){
        if(this.permesso != null){
            Permesso p = this.permesso;
            int codice = p.getCodice();
            this.elencoPermessi.put(codice,p);
            System.out.println("Permesso inserito correttamente: \n" + p);
        }
    }

}