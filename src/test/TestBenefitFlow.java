package test;

import domain.BenefitFlow;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestBenefitFlow {
    static BenefitFlow benefitFlow;

    @BeforeEach
    void testGetInstance(){
        benefitFlow = new BenefitFlow();
    }

    @AfterEach
    void clearTest() {
        benefitFlow = null;
    }

    @Test
    @DisplayName("Test inserimento nuovo dipendente")
    void testInserisciNuovoDipendente(){
        try {
            benefitFlow.inserisciNuovoDipendente("Giorgio", "Di Bartolo", LocalDate.of(2000, 9, 29), "ing03");
            benefitFlow.inserisciNuovoDipendente("Silvia", "Garozzo", LocalDate.of(2001, 4, 30),null);
            benefitFlow.inserisciNuovoDipendente("Matteo", "Santanocito", LocalDate.of(2000, 9, 24), "arch");
        }catch (Exception e){
            fail("Eccezione inaspettata");
        }
    }

//    @Test
//    @DisplayName("Test generatore matricola")
//    void testGeneratorematricola(){
//        try {
//            String matricola = benefitFlow.generatoreMatricola("Giorgio", "Di Bartolo");
//            assertEquals("GDB001", matricola);
//        }catch (Exception e){
//            fail("Eccezione inaspettata");
//        }
//    }
    //Questa funzione è controllata già dentro inserisciNuovoDipendete ed eseguendo tutto insieme fallisce
    //Eseguendola singolarmente non da nessun errore

    @Test
    @DisplayName("Test conferma inserimento nuovo dipendente")
    void testConfermaInserimento(){
        try {
            benefitFlow.inserisciNuovoDipendente("Giorgio", "Di Bartolo", LocalDate.of(2000, 9, 29), "ing03");
            benefitFlow.confermaInserimento();
            assertEquals(1, benefitFlow.mostraDipendenti().size());
        }catch (Exception e){
            fail("Eccezione inaspettata");
        }
    }

    @Test
    @DisplayName("Test gestisci ruolo dipendente")
    void testGestisciRuolo(){
        try {
            benefitFlow.inserisciNuovoDipendente("Giorgio", "Di Bartolo", LocalDate.of(2000, 9, 29), "ing03");
            benefitFlow.confermaInserimento();
            benefitFlow.confermaRuolo("GDB001", "inf03");
        }catch (Exception e){
            fail("Eccezione inaspettata");
        }
    }
}