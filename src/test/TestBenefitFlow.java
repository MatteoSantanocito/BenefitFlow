package test;

import domain.BenefitFlow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestBenefitFlow {
    static BenefitFlow benefitFlow;

    @Test
    public void testGetInstance(){
        assertNotNull(BenefitFlow.getInstance());
    }
}
