package domain;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class BenefitFlow extends JFrame {

    private static BenefitFlow benefitFlow;
    private Dipendente dipendenteCorrente;
    private Map<String, Ruolo> ruoli;
    private Map<String, Dipendente> elencoDipendenti;
    private Login login;

    public BenefitFlow(){
        this.elencoDipendenti = new HashMap<>();
        this.ruoli = new HashMap<>();
        loadRuoli();
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


//    public void confermaRuolo(){
//        if(dipendenteCorrente != null){
//            System.out.println("Il nuovo ruolo assegnato al dipendente " + dipendenteCorrente.getMatricola() + " è: " + dipendenteCorrente.getRuolo());
//        }
//    }

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
}