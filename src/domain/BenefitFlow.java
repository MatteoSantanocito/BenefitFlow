package domain;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
        setResizable(false);
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


    /****** UC1 ******/
    boolean flag = false;
    public void inserisciNuovoDipendente(String nome, String cognome, LocalDate dataDiNascita, String matricola, String codiceRuolo){
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


    /****** UC2 ******/
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


    /****** UC3 ******/
    int counter = 0;
    public void inserisciFerie(String matricola, String motivazione, LocalDate dataInizio, LocalDate dataFine){
        counter += 1;
        Dipendente d = elencoDipendenti.get(matricola);
        String stato = "in elaborazione";
        int codice = counter;
        String tipo = "F";

        if(d != null){
            this.ferie = new Ferie(motivazione, stato, tipo, codice, matricola, dataInizio, dataFine);
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

    public void inserisciPermesso(String matricola, String motivazione, LocalDate data, LocalTime oraInizio, LocalTime oraFine){
        counter += 1;
        Dipendente d = elencoDipendenti.get(matricola);
        String stato = "in elaborazione";
        int codice = counter;
        String tipo = "P";

        if(d != null){
            this.permesso = new Permesso(motivazione, stato, tipo, codice, matricola, data, oraInizio, oraFine);
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


    /****** UC4 ******/
    public List<Benefit> mostraBenefit(String tipo){
        if (tipo.equals("F")) {
            List<Benefit> listFerie = new ArrayList<>();
            listFerie.addAll(elencoFerie.values());
            List<Benefit> filteredListFerie = new ArrayList<>();
            for (Benefit ferie : listFerie) {
               String stato = ferie.getStato();
               if(stato.equals("in elaborazione")){
                   filteredListFerie.add(ferie);
               }
            }
            return filteredListFerie;
        }

        if (tipo.equals("P")) {
            List<Benefit> listPermessi = new ArrayList<>();
            List<Benefit> filteredListPermessi = new ArrayList<>();
            listPermessi.addAll(elencoPermessi.values());
            for (Benefit permesso : listPermessi) {
                String stato = permesso.getStato();
                if(stato.equals("in elaborazione")){
                    filteredListPermessi.add(permesso);
                }
            }
            return filteredListPermessi;
        }
        return null;
    }

    public List<Benefit> sovrapposizioneBenefit(int codice){
        List<Benefit> listBenefit = new ArrayList<>();
        listBenefit.addAll(elencoFerie.values());
        listBenefit.addAll(elencoPermessi.values());
        LocalDate dataF, dataP;
        LocalTime oraP;
        for (Benefit b : listBenefit){
            if (b.getCodice() == codice){
                this.benefit = b;

                if(b.getTipo().equals("F")){
                    Ferie ferie = (Ferie) b;
                    dataF = ferie.getDataInizio();
                    List<Ferie> listFerie = new ArrayList<>();
                    listFerie.addAll(elencoFerie.values());
                    List<Benefit> listFerieSovrapposizioni = new ArrayList<>();
                    for (Ferie f : listFerie){
                        if(f.getDataInizio().isEqual(dataF) && f.getStato().equals("in elaborazione")){
                            listFerieSovrapposizioni.add(f);
                        }
                    }
                    return listFerieSovrapposizioni;
                }

                if(b.getTipo().equals("P")){
                    Permesso permesso = (Permesso) b;
                    oraP = permesso.getOraInizio();
                    dataP = permesso.getData();
                    List<Permesso> listPermessi = new ArrayList<>();
                    listPermessi.addAll(elencoPermessi.values());
                    List<Benefit> listPermessiSovrapposizioni = new ArrayList<>();
                    for (Permesso p : listPermessi){
                        if( (p.getData().isEqual(dataP)) && (p.getOraInizio().equals(oraP)) && p.getStato().equals("in elaborazione")){
                            listPermessiSovrapposizioni.add(p);
                        }
                    }
                    return listPermessiSovrapposizioni;
                }
                break;
            }
        }
        return null;
    }

    public void confermaBenefit(String stato){
        this.benefit.setStato(stato);
    }


    /****** UC5 ******/
    public List<Benefit> mostraBenefitApprovati(String matricola, String tipo){
        //da sistemare
        this.dipendenteCorrente = elencoDipendenti.get(matricola);

        if (tipo.equals("F")) {
            List<Benefit> listFerie = new ArrayList<>();
            listFerie.addAll(elencoFerie.values());
            List<Benefit> approvedListFerie = new ArrayList<>();

            for (Benefit ferie : listFerie) {
               String stato = ferie.getStato();
               String m = ferie.getMatricola();

               if(stato.equals("approvato") && m.equals(matricola)){
                    approvedListFerie.add(ferie);
                }
            }
            return approvedListFerie;
        }

        if (tipo.equals("P")) {
            List<Benefit> listPermessi = new ArrayList<>();
            listPermessi.addAll(elencoPermessi.values());
            List<Benefit> approvedListPermessi = new ArrayList<>();

            for (Benefit permesso : listPermessi) {
               String stato = permesso.getStato();
               String m = permesso.getMatricola();

               if(stato.equals("approvato") && m.equals(matricola)){
                    approvedListPermessi.add(permesso);
                }
            }
            return approvedListPermessi;
        }

        return null;
    }

    public void richiediProlungamentoFerie(int codice, LocalDate dataFine){
        List<Benefit> listFerieApprovate = mostraBenefitApprovati(this.dipendenteCorrente.getMatricola(), "F");
        for (Benefit ferie : listFerieApprovate) {
            int c = ferie.getCodice();

            if(c == codice){
                String motivazione = ferie.getMotivazione();
                String matricola = ferie.getMatricola();
                LocalDate dataInizio = ((Ferie) ferie).getDataInizio();

                elencoFerie.remove(c);

                inserisciFerie(matricola, motivazione, dataInizio, dataFine);

                break;
            }
        }
    }

    public void confermaProlungamentoFerie(){
        confermaFerie();
    }

    public void richiediProlungamentoPermesso(int codice, LocalTime oraFine){
        List<Benefit> listPermessiApprovati = mostraBenefitApprovati(this.dipendenteCorrente.getMatricola(), "P");
        for (Benefit permesso : listPermessiApprovati) {
            int c = permesso.getCodice();

            if(c == codice){
                String motivazione = permesso.getMotivazione();
                String matricola = permesso.getMatricola();
                LocalDate data = ((Permesso) permesso).getData();
                LocalTime oraInizio = ((Permesso) permesso).getOraInizio();

                elencoPermessi.remove(c);

                inserisciPermesso(matricola, motivazione, data, oraInizio, oraFine);

                break;
            }
        }
    }

    public void confermaProlungamentoPermesso(){
        confermaPermesso();
    }


    /****** UC6 ******/
    public List<Benefit> visualizzaCongediComplessivi(String tipo){
        if (tipo.equals("F")) {
            List<Benefit> listFerie = new ArrayList<>();
            listFerie.addAll(elencoFerie.values());
            return listFerie;
        }

        if (tipo.equals("P")) {
            List<Benefit> listPermessi = new ArrayList<>();
            listPermessi.addAll(elencoPermessi.values());
            return listPermessi;
        }

        return null;
    }


}