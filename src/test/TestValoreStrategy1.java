package test;

import domain.ValoreStrategy1;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
public class TestValoreStrategy1 {

    @Test
    @DisplayName("Test pattern Strategy")
    void testCalcolaValore(){
        ValoreStrategy1 vs = new ValoreStrategy1();
        assertEquals(210, vs.calcolaValore(150, 1));

        assertEquals(240, vs.calcolaValore(150, 2));

        assertEquals(270, vs.calcolaValore(150, 3));

        assertEquals(0, vs.calcolaValore(150, 4));
    }
}
