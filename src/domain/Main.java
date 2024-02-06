package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        BenefitFlow benefitFlow = BenefitFlow.getInstance();
        benefitFlow.initComponent();

        System.out.println("\n\nInserimento nuovo dipendente...");
        benefitFlow.inserisciNuovoDipendente("Giorgio", "Di Bartolo", LocalDate.of(2024, 8, 5), "GDB001", "ing03");
        benefitFlow.confermaInserimento();
        benefitFlow.inserisciNuovoDipendente("Silvia", "Garozzo", LocalDate.of(2024, 8, 5), "SGZ002", "inf01");
        benefitFlow.confermaInserimento();
        benefitFlow.inserisciNuovoDipendente("Matteo", "Santanocito", LocalDate.of(2024, 8, 5), "MSN003", "inf03");
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
        LocalDate d1 = LocalDate.of(2024, 7, 30);
        benefitFlow.inserisciFerie("GDB001", "viaggio in un paese bellissimo ma immaginario perché devo studiare vaffanculo al mondo :)", d1, LocalDate.of(2024, 8, 5));
        benefitFlow.confermaFerie();
        System.out.println("\n");
        benefitFlow.inserisciPermesso("GDB001", "spesa", d1, LocalTime.of(10, 15), LocalTime.of(10, 45));
        benefitFlow.confermaPermesso();
        System.out.println("\n");
        // benefitFlow.inserisciFerie("SGZ002", "viaggio", "30/08/2024", "05/09/2024");
        // benefitFlow.confermaFerie();
    }
}