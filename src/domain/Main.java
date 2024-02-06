package domain;

public class Main {
    public static void main(String[] args) {
        BenefitFlow benefitFlow = BenefitFlow.getInstance();
        benefitFlow.initComponent();

        System.out.println("\n\nInserimento nuovo dipendente...");
        benefitFlow.inserisciNuovoDipendente("Giorgio", "Di Bartolo", "29/09/2000", "GDB001", "ing03");
        benefitFlow.confermaInserimento();
        benefitFlow.inserisciNuovoDipendente("Silvia", "Garozzo", "29/09/2000", "SGZ002", "inf01");
        benefitFlow.confermaInserimento();
        benefitFlow.inserisciNuovoDipendente("Matteo", "Santanocito", "24/09/2000", "MSN003", "inf03");
        benefitFlow.confermaInserimento();
//
//
//        List<Dipendente> listDipendenti = new ArrayList<>();
//        listDipendenti = benefitFlow.mostraDipendenti();
//        System.out.println("\n\nStampo la lista completa dei dipendenti...\n" + listDipendenti + "\n\n");
//
//        System.out.println("Assegno un ruolo al dipendente GDB0001...");
//        benefitFlow.gestisciRuolo("GDB0001", "ing01");
//        benefitFlow.confermaRuolo();
//
//        System.out.println("Assegno un ruolo al dipendente SDB0002...");
//        benefitFlow.gestisciRuolo("SDB0002", "ing04");
//        benefitFlow.confermaRuolo();
//
//        listDipendenti = benefitFlow.mostraDipendenti();
//        System.out.println("\n\nStampo la lista completa dei dipendenti aggiornata...\n" + listDipendenti + "\n\n");


        System.out.println("\n\nInserimento nuova ferie...");
        benefitFlow.inserisciFerie("GDB001", "viaggio", "30/07/2024", "05/08/2024");
        benefitFlow.confermaFerie();
        System.out.println("\n");
        benefitFlow.inserisciPermesso("GDB001", "spesa", "30/07/2024", "10:15", "10:30");
        benefitFlow.confermaPermesso();
        System.out.println("\n");
        benefitFlow.inserisciFerie("SGZ002", "viaggio", "30/08/2024", "05/09/2024");
        benefitFlow.confermaFerie();
    }
}