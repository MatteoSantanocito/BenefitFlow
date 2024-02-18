package domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        BenefitFlow benefitFlow = BenefitFlow.getInstance();
        benefitFlow.initComponent();

        //inserimento dipendenti per testare le funzionalità
        benefitFlow.inserisciNuovoDipendente("Giorgio", "Di Bartolo", LocalDate.of(2024, 8, 5), "ing03");
        benefitFlow.confermaInserimento();
        benefitFlow.inserisciNuovoDipendente("Silvia", "Garozzo", LocalDate.of(2024, 8, 5), "inf01");
        benefitFlow.confermaInserimento();
        benefitFlow.inserisciNuovoDipendente("Matteo", "Santanocito", LocalDate.of(2024, 8, 5), "inf03");
        benefitFlow.confermaInserimento();

        //inserimento ferie e permessi per testare le funzionalità
        LocalDate d1 = LocalDate.of(2024, 7, 30);
        benefitFlow.inserisciFerie("GDB001", "Vacanza", d1, LocalDate.of(2024, 8, 5));
        benefitFlow.confermaFerie();
        System.out.println("\n");
        benefitFlow.inserisciFerie("SGR002", "Laurea Matteo", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 2));
        benefitFlow.confermaFerie();
        System.out.println("\n");
        benefitFlow.inserisciPermesso("GDB001", "Visita medica", d1, LocalTime.of(10, 15), LocalTime.of(10, 45));
        benefitFlow.confermaPermesso();
        System.out.println("\n");
        benefitFlow.inserisciPermesso("SGR002", "Esame", d1, LocalTime.of(10, 15), LocalTime.of(10, 45));
        benefitFlow.confermaPermesso();
        System.out.println("\n");
        benefitFlow.inserisciPermesso("MSN003", "Studente lavoratore", d1, LocalTime.of(10, 30), LocalTime.of(11, 45));
        benefitFlow.confermaPermesso();
        System.out.println("\n");

        //creazione buono pasto scaduto per testare i metodi di visualizzazione
        benefitFlow.creaBuonoPasto("GDB001", LocalDate.of(2024, 2, 14));
        benefitFlow.confermaBuonoPasto();
    }
}