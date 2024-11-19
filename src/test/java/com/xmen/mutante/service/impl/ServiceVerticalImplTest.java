package com.xmen.mutante;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xmen.mutante.service.ServiceVertical;
import com.xmen.mutante.service.impl.ServiceVerticalImpl;
import com.xmen.mutante.utils.Utils;

public class ServiceVerticalImplTest {

    private ServiceVertical serviceVertical;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        serviceVertical = new ServiceVerticalImpl();
    }

    @Test
    void testHasVerticalSequence_ConSecuenciaVertical() {
        String[] dna = {
            "ATGCGA",
            "ATGCGA",
            "CTACGT",
            "ATACGA",  // Secuencia vertical AAAA
            "CCACTA",
            "TCACTG"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertTrue(serviceVertical.hasVerticalSequence(matrix));
    }

    @Test
    void testHasVerticalSequence_SinSecuenciaVertical() {
        String[] dna = {
            "ATGCGA",
            "CAGTGC",
            "TTATAT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertFalse(serviceVertical.hasVerticalSequence(matrix));
    }

    @Test
    void testCheckVertical_SecuenciaValida() {
        String[] dna = {
            "ATGCGA",
            "ATGCGA",
            "ATGCGA",
            "ATGCGA",  // Secuencia vertical AAAA en primera columna
            "CCCCTA",
            "TCACTG"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertTrue(serviceVertical.checkVertical(matrix, 0, 0));
    }

    @Test
    void testCheckVertical_SecuenciaInvalida() {
        String[] dna = {
            "AQGCGA",  // letra Q
            "CTGCGA",
            "ATGCGA",
            "ATGCGA",
            "CCCCTA",
            "TCACTG"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertFalse(serviceVertical.checkVertical(matrix, 0, 0));
    }

    @Test
    void testCheckVertical_FueraDelLimite() {
        String[] dna = {
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCTTA",
            "TCACTG"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        // Intentar verificar una secuencia vertical desde la última fila
        assertFalse(serviceVertical.checkVertical(matrix, 3, 0));
    }


    @Test
    void testHasVerticalSequence_SecuenciaAlFinal() {
        String[] dna = {
            "ATGCGA",
            "GTGCGA",
            "ATGCGA",
            "ATGCGA",  // Secuencia vertical AAAA al final
            "CCCCTA",
            "TCACTG"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertTrue(serviceVertical.hasVerticalSequence(matrix));
    }

    @Test
    void testHasVerticalSequence_MatrizMinima4x4() {
        String[] dna = {
            "AAAA",
            "AAAA",
            "AAAA",
            "AAAA"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertTrue(serviceVertical.hasVerticalSequence(matrix));
    }


    @Test
    void testCheckVertical_ConEspacioEnBlanco() {
        String[] dna = {
            "A GCGA",
            "A GCGA",
            "A GCGA",
            "A GCGA",
            "CCCCTA",
            "TCACTG"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertFalse(serviceVertical.checkVertical(matrix, 0, 1)); // Verificar posición con espacio
    }

    @Test
    void testHasVerticalSequence_MatrizGrande() {
        String[] dna = {
            "ATGCGAA",
            "ATGCGAA",
            "ATGCGAA",
            "ATGCGAA",
            "CCCCTAA",
            "TCACTGA",
            "TCACTGA"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertTrue(serviceVertical.hasVerticalSequence(matrix));
    }
}