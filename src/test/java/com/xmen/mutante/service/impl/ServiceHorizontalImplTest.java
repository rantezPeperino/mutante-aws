package com.xmen.mutante;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xmen.mutante.exception.InvalidMutanteException;
import com.xmen.mutante.service.impl.ServiceHorizontalImpl;
import com.xmen.mutante.utils.Utils;

public class ServiceHorizontalImplTest {

    private ServiceHorizontalImpl serviceHorizontal;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        serviceHorizontal = new ServiceHorizontalImpl();
    }

    @Test
    void testHasHorizontalSequence_ConSecuenciaHorizontal() {
        String[] dna = {
            "ATGCGA",
            "AAAATT",  // Secuencia horizontal AAAA
            "AGAGTC",
            "TTATGT",
            "GCGCTA",
            "TCACTG"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertTrue(serviceHorizontal.hasHorizontalSequence(matrix));
    }

    @Test
    void testHasHorizontalSequence_SinSecuenciaHorizontal() {
        String[] dna = {
            "ATGCGA",
            "AGATTT",
            "AGAGTC",
            "TTATGT",
            "GCGCTA",
            "TCACTG"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertFalse(serviceHorizontal.hasHorizontalSequence(matrix));
    }

    @Test
    void testHasHorizontalSequence_SecuenciaAlFinal() {
        String[] dna = {
            "ATGCGA",
            "AGATTT",
            "AGAGTC",
            "TTATGT",
            "GCGCTA",
            "TTTTAG"  // Secuencia horizontal al final
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertTrue(serviceHorizontal.hasHorizontalSequence(matrix));
    }

    @Test
    void testCheckHorizontal_SecuenciaValida() {
        String[] dna = {
            "AAAAGT",
            "CCCCTA",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertTrue(serviceHorizontal.checkHorizontal(matrix, 0, 0));
    }

    @Test
    void testCheckHorizontal_SecuenciaInvalida() {
        String[] dna = {
            "ATCAGT",
            "CCCCTA",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
        };
        
        char[][] matrix = Utils.convertToMatrix(dna);
        assertFalse(serviceHorizontal.checkHorizontal(matrix, 0, 0));
    }

    @Test
    void testMatrizNoValida_Null() {
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            Utils.isValidMatNxN(null);
        });
        
        String expectedMessage = "La secuencia ADN no puede ser null o vacia";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testMatrizNoValida_Vacia() {
        String[] dna = {};
        
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            Utils.isValidMatNxN(dna);
        });
        
        String expectedMessage = "La secuencia ADN no puede ser null o vacia";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testMatrizNoValida_DimensionesIncorrectas() {
        String[] dna = {
            "ATCAGT",
            "CCCCTA",
            "TTATGT",
            "AGAAGG",
            "CCCCTA"  // Falta una fila para ser 6x6
        };
        
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            Utils.isValidMatNxN(dna);
        });
        
        String expectedMessage = "No coincide las dimensiones de la matriz";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testMatrizNoValida_BaseInvalida() {
        String[] dna = {
            "ATCAGT",
            "CCCCTA",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTZ"  // Contiene Z que no es válida
        };
        
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            Utils.isValidMatNxN(dna);
        });
        
        String expectedMessage = "Invalido ADN, debe contener A, T, C, G";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testMatrizValida() {
        String[] dna = {
            "ATCAGT",
            "CCCCTA",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
        };
        
        assertTrue(Utils.isValidMatNxN(dna));
    }
}