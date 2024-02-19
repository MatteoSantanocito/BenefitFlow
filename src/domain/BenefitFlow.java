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

    private Congedo congedo;
    private Ferie ferie;
    private Permesso permesso;
    private BuonoPasto buonoPasto;
    private Map<String, Ferie> elencoFerie;
    private Map<String, Permesso> elencoPermessi;
    private Map<String, BuonoPasto> elencoBuoniPasto;


    public BenefitFlow(){
        this.elencoDipendenti = new HashMap<>();
        this.ruoli = new HashMap<>();
        loadRuoli();

        this.elencoFerie = new HashMap<>();
        this.elencoPermessi = new HashMap<>();
        this.elencoBuoniPasto = new HashMap<>();
    }

    public void initComponent() {
        login  = new Login(this);

        setLayout(new BorderLayout());
        add(login,BorderLayout.CENTER);

        setVisible(true);
        setResizable(false);
        setTitle("BenefitFlow");
        setSize(400, 320);
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
    public void inserisciNuovoDipendente(String nome, String cognome, LocalDate dataDiNascita, String codiceRuolo){
        Ruolo r = ruoli.get(codiceRuolo);
        String matricola = generatoreMatricola(nome,cognome);

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
    }

    public void confermaInserimento(){
        if(dipendenteCorrente != null){
            this.elencoDipendenti.put(dipendenteCorrente.getMatricola(),dipendenteCorrente);
            System.out.println("Lista dipendenti aggiornata con successo!");
            System.out.println(dipendenteCorrente);
        }
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
    private int counterF = 0;
    public void inserisciFerie(String matricola, String motivazione, LocalDate dataInizio, LocalDate dataFine){
        counterF += 1;
        Dipendente d = elencoDipendenti.get(matricola);
        String stato = "in elaborazione";
        String codice = "F" + counterF;
        String tipo = "F";

        if(d != null){
            this.ferie = new Ferie(motivazione, stato, tipo, codice, matricola, dataInizio, dataFine);
        }
    }

    public Ferie getFerieCorrente(){
        return ferie;
    }

    public void confermaFerie(){
        if(this.ferie != null){
            Ferie f = this.ferie;
            String codice = f.getCodice();
            this.elencoFerie.put(codice,f);
            System.out.println("Ferie inserita correttamente: \n" + f);
        }
    }

    private int counterP = 0;
    public void inserisciPermesso(String matricola, String motivazione, LocalDate data, LocalTime oraInizio, LocalTime oraFine){
        counterP += 1;
        Dipendente d = elencoDipendenti.get(matricola);
        String stato = "in elaborazione";
        String codice = "P" + counterP;
        String tipo = "P";

        if(d != null){
            this.permesso = new Permesso(motivazione, stato, tipo, codice, matricola, data, oraInizio, oraFine);
        }
    }

    public void confermaPermesso(){
        if(this.permesso != null){
            Permesso p = this.permesso;
            String codice = p.getCodice();
            this.elencoPermessi.put(codice,p);
            System.out.println("Permesso inserito correttamente: \n" + p);
        }
    }


    /****** UC4 ******/
    public List<Congedo> mostraBenefit(String tipo){
        if (tipo.equals("F")) {
            List<Congedo> listFerie = new ArrayList<>();
            listFerie.addAll(elencoFerie.values());
            List<Congedo> filteredListFerie = new ArrayList<>();
            for (Congedo ferie : listFerie) {
               String stato = ferie.getStato();
               if(stato.equals("in elaborazione")){
                   filteredListFerie.add(ferie);
               }
            }
            return filteredListFerie;
        }

        if (tipo.equals("P")) {
            List<Congedo> listPermessi = new ArrayList<>();
            List<Congedo> filteredListPermessi = new ArrayList<>();
            listPermessi.addAll(elencoPermessi.values());
            for (Congedo permesso : listPermessi) {
                String stato = permesso.getStato();
                if(stato.equals("in elaborazione")){
                    filteredListPermessi.add(permesso);
                }
            }
            return filteredListPermessi;
        }
        return null;
    }

    public List<Congedo> sovrapposizioneBenefit(String codice){
        List<Congedo> listCongedo = new ArrayList<>();
        listCongedo.addAll(elencoFerie.values());
        listCongedo.addAll(elencoPermessi.values());
        LocalDate dataF, dataP;
        LocalTime oraP;
        for (Congedo b : listCongedo){
            if (b.getCodice().equals(codice)){
                this.congedo = b;

                if(b.getTipo().equals("F")){
                    Ferie ferie = (Ferie) b;
                    dataF = ferie.getDataInizio();
                    List<Ferie> listFerie = new ArrayList<>();
                    listFerie.addAll(elencoFerie.values());
                    List<Congedo> listFerieSovrapposizioni = new ArrayList<>();
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
                    List<Congedo> listPermessiSovrapposizioni = new ArrayList<>();
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
        this.congedo.setStato(stato);
    }


    /****** UC5 ******/
    public List<Congedo> mostraBenefitApprovati(String matricola, String tipo){
        this.dipendenteCorrente = elencoDipendenti.get(matricola);

        if (tipo.equals("F")) {
            List<Congedo> listFerie = new ArrayList<>();
            listFerie.addAll(elencoFerie.values());
            List<Congedo> approvedListFerie = new ArrayList<>();

            for (Congedo ferie : listFerie) {
               String stato = ferie.getStato();
               String m = ferie.getMatricola();

               if(stato.equals("approvato") && m.equals(matricola)){
                    approvedListFerie.add(ferie);
                }
            }
            return approvedListFerie;
        }

        if (tipo.equals("P")) {
            List<Congedo> listPermessi = new ArrayList<>();
            listPermessi.addAll(elencoPermessi.values());
            List<Congedo> approvedListPermessi = new ArrayList<>();

            for (Congedo permesso : listPermessi) {
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

    public void richiediProlungamentoFerie(String codice, LocalDate dataFine){
        List<Congedo> listFerieApprovate = mostraBenefitApprovati(this.dipendenteCorrente.getMatricola(), "F");

        for (Congedo ferie : listFerieApprovate) {
            String c = ferie.getCodice();

            if(c.equals(codice)){
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

    public void richiediProlungamentoPermesso(String codice, LocalTime oraFine){
        List<Congedo> listPermessiApprovati = mostraBenefitApprovati(this.dipendenteCorrente.getMatricola(), "P");

        for (Congedo permesso : listPermessiApprovati) {
            String c = permesso.getCodice();

            if(c.equals(codice)){
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
    public List<Congedo> visualizzaCongediComplessivi(String tipo){
        if (tipo.equals("F")) {
            List<Congedo> listFerie = new ArrayList<>();
            listFerie.addAll(elencoFerie.values());
            return listFerie;
        }

        if (tipo.equals("P")) {
            List<Congedo> listPermessi = new ArrayList<>();
            listPermessi.addAll(elencoPermessi.values());
            return listPermessi;
        }

        return null;
    }


    /****** UC7 ******/
    public List<Congedo> visualizzaCongedi(String tipo, String matricola){
        if (tipo.equals("F")) {
            List<Congedo> listFerie = new ArrayList<>();
            listFerie.addAll(elencoFerie.values());
            List<Congedo> listFerieDip = new ArrayList<>();
            for (Congedo f : listFerie){
                if(f.getMatricola().equals(matricola)){
                    listFerieDip.add(f);
                }
            }
            return listFerieDip;
        }

        if (tipo.equals("P")) {
            List<Congedo> listPermessi = new ArrayList<>();
            listPermessi.addAll(elencoPermessi.values());
            List<Congedo> listPermessiDip = new ArrayList<>();
            for (Congedo p : listPermessi){
                if(p.getMatricola().equals(matricola)){
                    listPermessiDip.add(p);
                }
            }
            return listPermessiDip;
        }

        return null;
    }


    /****** UC8 ******/
    public List<Dipendente> visualizzaDipendenti(){
        return mostraDipendenti();
    }

    private int counterBP = 0;
    public void creaBuonoPasto(String matricola, LocalDate dataScadenza){
        counterBP += 1;
        Dipendente d = elencoDipendenti.get(matricola);
        String stato = "valido";
        String codice = "BP" + counterBP;
        float valoreBase = 150.00f;

        if(d != null){
            if(d.getRuolo() != null){
                int livello = d.getRuolo().getLivello();
                ValoreStrategyFactory sf = ValoreStrategyFactory.getInstance();
                ValoreStrategyInterface vs = sf.getValoreStrategy();
                float valore = vs.calcolaValore(valoreBase, livello);
                this.buonoPasto = new BuonoPasto(codice, stato, matricola, dataScadenza, valore);
            }
        }
    }

    public BuonoPasto getBuonoPastoCorrente(){
        return buonoPasto;
    }

    public void confermaBuonoPasto(){
        if(this.buonoPasto != null){
            BuonoPasto bp = this.buonoPasto;
            String codice = bp.getCodiceBP();
            this.elencoBuoniPasto.put(codice,bp);
            System.out.println("Buono Pasto creato correttamente: \n" + bp);
        }
    }


    /****** UC9 ******/
    public List<BuonoPasto> visualizzaBuoniPastoValidi(String matricola){
        List<BuonoPasto> listBuoniPasto = new ArrayList<>();
        listBuoniPasto.addAll(elencoBuoniPasto.values());
        List<BuonoPasto> filteredListBuoniPasto = new ArrayList<>();
        controlloScadenzaBP();
        for(BuonoPasto bp : listBuoniPasto){
            if(bp.getMatricola().equals(matricola) && bp.getStato().equals("valido")){
                filteredListBuoniPasto.add(bp);
            }
        }
        return filteredListBuoniPasto;
    }

    public void confermaAttivazione(String codice){
        List<BuonoPasto> listBuoniPasto = new ArrayList<>();
        listBuoniPasto.addAll(elencoBuoniPasto.values());
        for(BuonoPasto bp : listBuoniPasto){
            if(bp.getCodiceBP().equals(codice)){
                String stato = bp.getStato();
                if(stato.equals("valido")){
                    bp.setStato("attivato");
                }
            }
        }
    }


    /****** UC10 ******/
    public List<BuonoPasto> visualizzaBuoniPasto(String matricola){
        List<BuonoPasto> listBuoniPasto = new ArrayList<>();
        listBuoniPasto.addAll(elencoBuoniPasto.values());
        List<BuonoPasto> filteredListBuoniPasto = new ArrayList<>();
        controlloScadenzaBP();
        for(BuonoPasto bp : listBuoniPasto){
            if(bp.getMatricola().equals(matricola)){
                filteredListBuoniPasto.add(bp);
            }
        }
        return filteredListBuoniPasto;
    }


    /****** UTILITY ******/
    public List<BuonoPasto> visualizzaBuoniPastoComplessivi(){
        List<BuonoPasto> listBuoniPasto = new ArrayList<>();
        listBuoniPasto.addAll(elencoBuoniPasto.values());
        List<BuonoPasto> filteredListBuoniPasto = new ArrayList<>();
        for(BuonoPasto bp : listBuoniPasto){
            if(bp.getDataScadenza().isAfter(LocalDate.now()) || bp.getDataScadenza().isEqual(LocalDate.now())){
                filteredListBuoniPasto.add(bp);
            }
        }
        return filteredListBuoniPasto;
    }

    public List<Ruolo> getRuoli(){
        List<Ruolo> listRuolo = new ArrayList<>();
        listRuolo.addAll(ruoli.values());
        return listRuolo;
    }

    private int counter = 1;
    public String generatoreMatricola(String nome, String cognome) {
        String nomeUpper = nome.toUpperCase();
        String cognomeUpper = cognome.toUpperCase();
        char firstCharNome = nomeUpper.charAt(0);
        char firstCharCognome = cognomeUpper.charAt(0);
        char secondCharCognome = cognomeUpper.charAt(2);
        for (int i = 1; i < cognomeUpper.length(); i++) {
            if (Character.isWhitespace(cognomeUpper.charAt(i - 1))) {
                secondCharCognome = cognomeUpper.charAt(i);
                break;
            }
        }

        String counterString = String.format("%03d", counter);
        counter++;
        return String.valueOf(firstCharNome) + firstCharCognome + secondCharCognome + counterString;
    }

    public void controlloScadenzaBP(){
        List<BuonoPasto> listBuoniPasto = new ArrayList<>();
        listBuoniPasto.addAll(elencoBuoniPasto.values());
        for(BuonoPasto bp : listBuoniPasto){
            if(bp.getDataScadenza().isBefore(LocalDate.now()) && bp.getStato().equals("valido")){
                bp.setStato("scaduto");
            }
        }
    }
}