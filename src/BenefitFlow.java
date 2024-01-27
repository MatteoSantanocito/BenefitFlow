import java.util.*;

public class BenefitFlow {

    private static BenefitFlow benefitFlow;
    private Dipendente dipendenteCorrente;
    private Map<String,Ruolo> ruoli;
    private Map<String,Dipendente> elencoDipendenti;

    private BenefitFlow(){
        this.elencoDipendenti = new HashMap<>();
        this.ruoli = new HashMap<>();
        loadRuoli();
    }

    public static BenefitFlow getInstance(){
        if(benefitFlow == null){
            benefitFlow = new BenefitFlow();
        }else{
            System.out.println("Istanza già creata");
        }
        return benefitFlow;
    }

    public void inserisciNuovoDipendente(String nome, String cognome, Date dataDiNascita, String matricola, String codiceRuolo){
        Ruolo r = ruoli.get(codiceRuolo);
        if(r != null){
            this.dipendenteCorrente = new Dipendente(nome,cognome,dataDiNascita,matricola,r);
            System.out.println("Dipendente Inserito!");
        }else{
            System.out.println("Dipendente non insertito!");
        }
    }

    public void confermaInserimento(){
        if(dipendenteCorrente != null){
            this.elencoDipendenti.put(dipendenteCorrente.getMatricola(),dipendenteCorrente);
            System.out.println("inserimento Dipendente effettuato");
        }
    }

    public List<Dipendente> mostraDipendenti(){
        List<Dipendente> listDipendenti = new ArrayList<>();
        listDipendenti.addAll(elencoDipendenti.values());
        return listDipendenti;
    }

    public void gestisciRuolo(String matricola, String codiceRuolo){
        Dipendente d = elencoDipendenti.get(matricola);
        if(d != null){
            Ruolo r = d.getRuolo();
            if(r == null){
                Ruolo ruolo = ruoli.get(codiceRuolo);
                d.setRuolo(ruolo);
                System.out.println("Ruolo assegnato correttamente.");
            }
        }
    }

    public void confermaRuolo(){
        if(dipendenteCorrente != null){
            System.out.println("Hai modificato correttamente il ruolo del dipendente" + dipendenteCorrente);
        }
    }



    private void loadRuoli() {
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
