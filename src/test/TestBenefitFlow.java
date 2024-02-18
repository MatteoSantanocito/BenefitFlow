package test;

import domain.BenefitFlow;
import domain.Ruolo;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestBenefitFlow {
    private BenefitFlow benefitFlow;

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

    @Test
    @DisplayName("Test generatore matricola")
    void testGeneratorematricola(){
        try {
            String matricola = benefitFlow.generatoreMatricola("Giorgio", "Di Bartolo");
            assertEquals("GDB001", matricola);
        }catch (Exception e){
            fail("Eccezione inaspettata");
        }
    }

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

    @Test
    @DisplayName("Test LoadRuolo")
    void testLoadRuolo(){
        List<Ruolo> expected = new ArrayList<>();
        Ruolo r1 = new Ruolo("ing01", "dataAnalist", 1);
        Ruolo r2 = new Ruolo("ing02", "dataAnalist", 2);
        Ruolo r3 = new Ruolo("ing03", "dataAnalist", 3);
        Ruolo r4 = new Ruolo("inf01", "javaDev", 1);
        Ruolo r5 = new Ruolo("inf02", "javaDev", 2);
        Ruolo r6 = new Ruolo("inf03", "javaDev", 3);
        Ruolo r7 = new Ruolo("ing04", "softwareDev", 1);
        Ruolo r8 = new Ruolo("ing05", "softwareDev", 2);
        Ruolo r9 = new Ruolo("ing06", "softwareDev", 3);
        expected.add(r1);
        expected.add(r2);
        expected.add(r3);
        expected.add(r4);
        expected.add(r5);
        expected.add(r6);
        expected.add(r7);
        expected.add(r8);
        expected.add(r9);

        assertEquals(expected.size(), benefitFlow.getRuoli().size());
//        assertTrue(benefitFlow.getRuoli().containsAll(expected));
    }

    @Test
    @DisplayName("Test inserisci Ferie")
    void testInserisciFerie(){
        try {
            benefitFlow.inserisciNuovoDipendente("Silvia", "Garozzo", LocalDate.of(2000, 9, 29), "ing03");
            benefitFlow.confermaInserimento();
            benefitFlow.inserisciFerie("SGR001","vacanza", LocalDate.of(2024, 9, 29), LocalDate.of(2024, 10, 29));
            assertNotNull(benefitFlow.getFerieCorrente());
        }catch (Exception e){
            fail("Eccezione inaspettata");
        }
    }

    @Test
    @DisplayName("Test conferma inserimento Ferie")
    void testConfermaInserimentoFerie(){
        try {
            benefitFlow.inserisciNuovoDipendente("Silvia", "Garozzo", LocalDate.of(2000, 9, 29), "ing03");
            benefitFlow.confermaInserimento();
            benefitFlow.inserisciFerie("SGR001","vacanza", LocalDate.of(2024, 9, 29), LocalDate.of(2024, 10, 29));
            benefitFlow.confermaFerie();
            assertEquals(1, benefitFlow.mostraBenefit("F").size());
        }catch (Exception e){
            fail("Eccezione inaspettata");
        }
    }

    @Test
    @DisplayName("Test sovrapposizione Benefit")
    void testSovrapposizioneBenefit(){
        try{
            benefitFlow.inserisciNuovoDipendente("Silvia", "Garozzo", LocalDate.of(2000, 9, 29), "ing03");
            benefitFlow.confermaInserimento();
            benefitFlow.inserisciNuovoDipendente("Giorgio", "Di Bartolo", LocalDate.of(2000, 9, 29), "ing03");
            benefitFlow.confermaInserimento();
            benefitFlow.inserisciFerie("SGR001","vacanza", LocalDate.of(2024, 9, 29), LocalDate.of(2024, 10, 29));
            benefitFlow.confermaFerie();
            benefitFlow.inserisciFerie("GDB002","salute", LocalDate.of(2024, 9, 29), LocalDate.of(2024, 10, 30));
            benefitFlow.confermaFerie();
            benefitFlow.inserisciFerie("GDB002","laurea matteo", LocalDate.of(2024, 3, 1), LocalDate.of(2024, 3, 2));
            benefitFlow.confermaFerie();
            assertEquals(2,benefitFlow.sovrapposizioneBenefit("F1").size());
        }catch (Exception e){
            fail("Eccezione inaspettata");
        }
    }

    @Test
    @DisplayName("Test creazione Buono Pasto")
    void testCreazioneBuonoPasto(){
        try{
            benefitFlow.inserisciNuovoDipendente("Silvia", "Garozzo", LocalDate.of(2000, 9, 29), "ing03");
            benefitFlow.confermaInserimento();
            benefitFlow.creaBuonoPasto("SGR001", LocalDate.of(2024,10,10));
            assertNotNull(benefitFlow.getBuonoPastoCorrente());
        }catch (Exception e){
            fail("Eccezione inaspettata");
        }
    }

    @Test
    @DisplayName("Test conferma Buono Pasto")
    void testConfermaBuonoPasto(){
        try{
            benefitFlow.inserisciNuovoDipendente("Silvia", "Garozzo", LocalDate.of(2000, 9, 29), "ing03");
            benefitFlow.confermaInserimento();
            benefitFlow.creaBuonoPasto("SGR001", LocalDate.of(2024,10,10));
            benefitFlow.confermaBuonoPasto();
            assertEquals(1, benefitFlow.visualizzaBuoniPastoComplessivi().size());
        }catch (Exception e){
            fail("Eccezione inaspettata");
        }
    }


}
