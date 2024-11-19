package com.xmen.mutante;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.xmen.mutante.exception.InvalidMutanteException;
import com.xmen.mutante.utils.Utils;

public class UtilsTest {

    @Test
    void testValidMatrix() {
        String[] dna = {
            "ATCG",
            "TACG",
            "CGAT",
            "GCTA"
        };
        assertTrue(Utils.isValidMatNxN(dna));
    }

    @Test
    void testNullMatrix() {
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            Utils.isValidMatNxN(null);
        });
        assertEquals("La secuencia ADN no puede ser null o vacia", exception.getMessage());
    }

    @Test
    void testEmptyMatrix() {
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            Utils.isValidMatNxN(new String[]{});
        });
        assertEquals("La secuencia ADN no puede ser null o vacia", exception.getMessage());
    }

    @Test
    void testNullRow() {
        String[] dna = {
            "ATCG",
            null,
            "CGAT",
            "GCTA"
        };
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            Utils.isValidMatNxN(dna);
        });
        assertEquals("Row null is null", exception.getMessage());
    }

    @Test
    void testInvalidDimensions() {
        String[] dna = {
            "ATCG",
            "TAC",  // Esta fila tiene longitud diferente
            "CGAT",
            "GCTA"
        };
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            Utils.isValidMatNxN(dna);
        });
        assertEquals("No coincide las dimensiones de la matriz", exception.getMessage());
    }

    @Test
    void testInvalidBase() {
        String[] dna = {
            "ATCG",
            "TAXG",  // Contiene un carácter 'X' inválido
            "CGAT",
            "GCTA"
        };
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            Utils.isValidMatNxN(dna);
        });
        assertEquals("Invalido ADN, debe contener A, T, C, G", exception.getMessage());
    }

    @Test
    void testDifferentMatrixSizes() {
        // Test con matriz 3x3
        String[] dna3x3 = {
            "ATG",
            "TCG",
            "GCA"
        };
        assertTrue(Utils.isValidMatNxN(dna3x3));

        // Test con matriz 5x5
        String[] dna5x5 = {
            "ATCGA",
            "TACGT",
            "CGATC",
            "GCTAT",
            "ATCGA"
        };
        assertTrue(Utils.isValidMatNxN(dna5x5));
    }

    @Test
    void testAllValidBases() {
        String[] dna = {
            "AAAA",
            "TTTT",
            "CCCC",
            "GGGG"
        };
        assertTrue(Utils.isValidMatNxN(dna));
    }
    

     @Test
    void testValidBases() {
        // Test cada base válida individualmente
        assertTrue(Utils.isValidBase('A'));
        assertTrue(Utils.isValidBase('T'));
        assertTrue(Utils.isValidBase('C'));
        assertTrue(Utils.isValidBase('G'));
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'T', 'C', 'G'})
    void testValidBasesParameterized(char base) {
        assertTrue(Utils.isValidBase(base));
    }

    @Test
    void testInvalidBases() {
        // Test bases inválidas
        assertFalse(Utils.isValidBase('B'));
        assertFalse(Utils.isValidBase('X'));
        assertFalse(Utils.isValidBase('Z'));
        assertFalse(Utils.isValidBase('1'));
        assertFalse(Utils.isValidBase(' '));
    }

    @ParameterizedTest
    @ValueSource(chars = {'B', 'X', 'Y', 'Z', '1', ' ', '-', '*'})
    void testInvalidBasesParameterized(char base) {
        assertFalse(Utils.isValidBase(base));
    }

    @Test
    void testCaseSensitivity() {
        // Test que verifica que el método es sensible a mayúsculas/minúsculas
        assertFalse(Utils.isValidBase('a'));
        assertFalse(Utils.isValidBase('t'));
        assertFalse(Utils.isValidBase('c'));
        assertFalse(Utils.isValidBase('g'));
    }

    @Test
    void testSpecialCharacters() {
        // Test con caracteres especiales
        assertFalse(Utils.isValidBase('\n'));
        assertFalse(Utils.isValidBase('\t'));
        assertFalse(Utils.isValidBase('\r'));
        assertFalse(Utils.isValidBase('\0'));
    }

    @Test
    void testUnicodeCharacters() {
        // Test con caracteres Unicode
        assertFalse(Utils.isValidBase('Á'));
        assertFalse(Utils.isValidBase('Ñ'));
        assertFalse(Utils.isValidBase('€'));
        assertFalse(Utils.isValidBase('♠'));
    }

    @Test
    void testNumericCharacters() {
        // Test con caracteres numéricos
        for (char c = '0'; c <= '9'; c++) {
            assertFalse(Utils.isValidBase(c), "Número " + c + " no debería ser una base válida");
        }
    }

    @Test
    void testAllASCIICharacters() {
        // Test con todos los caracteres ASCII imprimibles
        for (char c = 32; c < 127; c++) {
            if (c != 'A' && c != 'T' && c != 'C' && c != 'G') {
                assertFalse(Utils.isValidBase(c), 
                    "Carácter ASCII " + c + " (" + (int)c + ") no debería ser una base válida");
            }
        }
    }

        @Test
    void testBasicConversion() {
        String[] input = {
            "ATCG",
            "TACG",
            "CGAT",
            "GCTA"
        };
        
        char[][] expected = {
            {'A', 'T', 'C', 'G'},
            {'T', 'A', 'C', 'G'},
            {'C', 'G', 'A', 'T'},
            {'G', 'C', 'T', 'A'}
        };
        
        char[][] result = Utils.convertToMatrix(input);
        
        // Verificar dimensiones
        assertEquals(expected.length, result.length);
        assertEquals(expected[0].length, result[0].length);
        
        // Verificar contenido
        assertArrayEquals(expected, result);
    }

    @Test
    void testSingleCharacterMatrix() {
        String[] input = {"A"};
        char[][] expected = {{'A'}};
        
        char[][] result = Utils.convertToMatrix(input);
        
        assertEquals(1, result.length);
        assertEquals(1, result[0].length);
        assertArrayEquals(expected, result);
    }

    @Test
    void testLargeMatrix() {
        String[] input = {
            "ATCGATCG",
            "TAGCTAGC",
            "CGATCGAT",
            "GCTAGCTA",
            "ATCGATCG",
            "TAGCTAGC",
            "CGATCGAT",
            "GCTAGCTA"
        };
        
        char[][] result = Utils.convertToMatrix(input);
        
        // Verificar dimensiones
        assertEquals(8, result.length);
        assertEquals(8, result[0].length);
        
        // Verificar que cada fila se convirtió correctamente
        for (int i = 0; i < input.length; i++) {
            assertArrayEquals(input[i].toCharArray(), result[i]);
        }
    }

    @Test
    void testRectangularMatrix() {
        String[] input = {
            "ATCG",
            "TACG",
            "CGAT"
        };
        
        char[][] result = Utils.convertToMatrix(input);
        
        // Verificar dimensiones
        assertEquals(3, result.length);
        assertEquals(4, result[0].length);
        
        // Verificar contenido
        for (int i = 0; i < input.length; i++) {
            assertArrayEquals(input[i].toCharArray(), result[i]);
        }
    }

    @Test
    void testEmptyStrings() {
        String[] input = {"", "", ""};
        
        char[][] result = Utils.convertToMatrix(input);
        
        assertEquals(3, result.length);
        assertEquals(0, result[0].length);
    }

    @Test
    void testSpecialCharactersMore() {
        String[] input = {
            "A*C#",
            "T@C$",
            "C%A&"
        };
        
        char[][] expected = {
            {'A', '*', 'C', '#'},
            {'T', '@', 'C', '$'},
            {'C', '%', 'A', '&'}
        };
        
        char[][] result = Utils.convertToMatrix(input);
        
        assertArrayEquals(expected, result);
    }

    @Test
    void testNonAsciiCharacters() {
        String[] input = {
            "AÑCÉ",
            "TÆCØ",
            "CÑAÉ"
        };
        
        char[][] result = Utils.convertToMatrix(input);
        
        assertEquals(3, result.length);
        assertEquals(4, result[0].length);
        
        // Verificar que cada carácter se convirtió correctamente
        for (int i = 0; i < input.length; i++) {
            assertArrayEquals(input[i].toCharArray(), result[i]);
        }
    }

    @Test
    void testPerformanceWithLargeMatrix() {
        int size = 1000;
        String[] input = new String[size];
        String row = "A".repeat(size);
        
        // Crear una matriz grande
        for (int i = 0; i < size; i++) {
            input[i] = row;
        }
        
        long startTime = System.nanoTime();
        char[][] result = Utils.convertToMatrix(input);
        long endTime = System.nanoTime();
        
        // Verificar dimensiones
        assertEquals(size, result.length);
        assertEquals(size, result[0].length);
        
        // Verificar que el tiempo de ejecución sea razonable (menos de 1 segundo)
        assertTrue((endTime - startTime) / 1_000_000 < 1000, 
            "La conversión tomó demasiado tiempo");
    }

}
