package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        BenefitFlow benefitFlow = BenefitFlow.getInstance();
        benefitFlow.initComponent();

//        System.out.println("\n\nInserimento nuovo dipendente...");
//        benefitFlow.inserisciNuovoDipendente("Giorgio", "Di Bartolo", LocalDate.of(2024, 8, 5), "ing03");
//        benefitFlow.confermaInserimento();
//        benefitFlow.inserisciNuovoDipendente("Silvia", "Garozzo", LocalDate.of(2024, 8, 5), "inf01");
//        benefitFlow.confermaInserimento();
//        benefitFlow.inserisciNuovoDipendente("Matteo", "Santanocito", LocalDate.of(2024, 8, 5), "inf03");
//        benefitFlow.confermaInserimento();
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
        LocalDate d1 = LocalDate.of(2024, 7, 30);
        benefitFlow.inserisciFerie("GDB001", "Vacanza", d1, LocalDate.of(2024, 8, 5));
        benefitFlow.confermaFerie();
        System.out.println("\n");
        benefitFlow.inserisciFerie("SGR002", "salute", d1, LocalDate.of(2024, 8, 5));
        benefitFlow.confermaFerie();
        System.out.println("\n");
        benefitFlow.inserisciPermesso("GDB001", "Visita medica", d1, LocalTime.of(10, 15), LocalTime.of(10, 45));
        benefitFlow.confermaPermesso();
        System.out.println("\n");
        benefitFlow.inserisciPermesso("SGR002", "spesa", d1, LocalTime.of(10, 15), LocalTime.of(10, 45));
        benefitFlow.confermaPermesso();
        System.out.println("\n");
        benefitFlow.inserisciPermesso("MSN003", "laurea", d1, LocalTime.of(10, 30), LocalTime.of(10, 45));
        benefitFlow.confermaPermesso();
        System.out.println("\n");

        // benefitFlow.sovrapposizioneBenefit(3);
        // benefitFlow.confermaBenefit("approvato");

        // System.out.println("mostro permessi approvati: \n" + benefitFlow.mostraBenefitApprovati("GDB001", "P"));

        // benefitFlow.richiediProlungamentoPermesso(3, LocalTime.of(11, 00));
        // benefitFlow.confermaProlungamentoPermesso()

        benefitFlow.creaBuonoPasto("GDB001", LocalDate.of(2024, 2, 14));
        benefitFlow.confermaBuonoPasto();
    }
}