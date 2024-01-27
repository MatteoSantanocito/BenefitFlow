import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BenefitFlow benefitFlow = BenefitFlow.getInstance();
        benefitFlow.loadRuoli();

        System.out.println("\n\nInserimento nuovo dipendente...");
        Dipendente d1 = benefitFlow.inserisciNuovoDipendente("Giorgio", "Di Bartolo", "29/09/2000", "GDB0001", null);
        benefitFlow.confermaInserimento();

        List<Dipendente> listDipendenti = new ArrayList<>();
        listDipendenti = benefitFlow.mostraDipendenti();
        System.out.println("\n\nStampo la lista completa dei dipendenti...\n" + listDipendenti + "\n\n");

        System.out.println("Assegno un ruolo al dipendente GDB0001...");
        benefitFlow.gestisciRuolo("GDB0001", "ing01");
        benefitFlow.confermaRuolo();

        listDipendenti = benefitFlow.mostraDipendenti();
        System.out.println("\n\nStampo la lista completa dei dipendenti aggiornata...\n" + listDipendenti + "\n\n");
    }
}