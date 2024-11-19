package com.xmen.mutante;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xmen.mutante.service.impl.ServiceDiagonalImpl;

class ServiceDiagonalImplTest {
    private ServiceDiagonalImpl service;
    private static final int MATRIX_SIZE = 4;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        service = new ServiceDiagonalImpl();
    }

    @Test
    void checkDiagonal_ValidSequence_ReturnsTrue() {
        char[][] matrix = {
            {'A', 'T', 'G', 'C'},
            {'C', 'A', 'T', 'G'},
            {'G', 'C', 'A', 'T'},
            {'C', 'G', 'T', 'A'}
        };
        assertTrue(service.checkDiagonal(matrix, 0, 0));
    }

    @Test
    void checkDiagonal_InvalidSequence_ReturnsFalse() {
        char[][] matrix = {
            {'A', 'T', 'G', 'C'},
            {'C', 'T', 'T', 'G'},
            {'G', 'C', 'G', 'T'},
            {'C', 'G', 'T', 'A'}
        };
        assertFalse(service.checkDiagonal(matrix, 0, 0));
    }

    @Test
    void checkDiagonal_OutOfBounds_ReturnsFalse() {
        char[][] matrix = new char[MATRIX_SIZE][MATRIX_SIZE];
        assertFalse(service.checkDiagonal(matrix, MATRIX_SIZE - 1, MATRIX_SIZE - 1));
    }

    @Test
    void checkDiagonal_WithSpace_ReturnsFalse() {
        char[][] matrix = {
            {' ', 'T', 'G', 'C'},
            {'C', ' ', 'T', 'G'},
            {'G', 'C', ' ', 'T'},
            {'C', 'G', 'T', ' '}
        };
        assertFalse(service.checkDiagonal(matrix, 0, 0));
    }

    @Test
    void checkInverseDiagonal_ValidSequence_ReturnsTrue() {
        char[][] matrix = {
            {'A', 'T', 'G', 'C'},
            {'C', 'T', 'C', 'G'},
            {'G', 'C', 'T', 'T'},
            {'C', 'G', 'T', 'A'}
        };
        assertTrue(service.checkInverseDiagonal(matrix, 0, 3));
    }

    @Test
    void checkInverseDiagonal_InvalidSequence_ReturnsFalse() {
        char[][] matrix = {
            {'A', 'T', 'G', 'C'},
            {'C', 'T', 'T', 'G'},
            {'G', 'G', 'T', 'T'},
            {'C', 'G', 'T', 'A'}
        };
        assertFalse(service.checkInverseDiagonal(matrix, 0, 3));
    }

    @Test
    void checkInverseDiagonal_OutOfBounds_ReturnsFalse() {
        char[][] matrix = new char[MATRIX_SIZE][MATRIX_SIZE];
        assertFalse(service.checkInverseDiagonal(matrix, 0, 0));
    }

    @Test
    void checkInverseDiagonal_WithSpace_ReturnsFalse() {
        char[][] matrix = {
            {'A', 'T', 'G', ' '},
            {'C', 'T', ' ', 'G'},
            {'G', ' ', 'T', 'T'},
            {' ', 'G', 'T', 'A'}
        };
        assertFalse(service.checkInverseDiagonal(matrix, 0, 3));
    }

    @Test
    void hasDiagonalSequence_WithMainDiagonal_ReturnsTrue() {
        char[][] matrix = {
            {'A', 'T', 'G', 'C'},
            {'C', 'A', 'T', 'G'},
            {'G', 'C', 'A', 'T'},
            {'C', 'G', 'T', 'A'}
        };
        assertTrue(service.hasDiagonalSequence(matrix));
    }

    @Test
    void hasDiagonalSequence_WithInverseDiagonal_ReturnsTrue() {
        char[][] matrix = {
            {'A', 'T', 'G', 'C'},
            {'C', 'T', 'C', 'G'},
            {'G', 'C', 'T', 'T'},
            {'C', 'G', 'T', 'A'}
        };
        assertTrue(service.hasDiagonalSequence(matrix));
    }

    @Test
    void hasDiagonalSequence_WithNoSequence_ReturnsFalse() {
        char[][] matrix = {
            {'A', 'T', 'G', 'C'},
            {'C', 'G', 'T', 'G'},
            {'G', 'C', 'A', 'T'},
            {'C', 'G', 'T', 'A'}
        };
        assertFalse(service.hasDiagonalSequence(matrix));
    }

    @Test
    void hasDiagonalSequence_WithNullMatrix_ReturnsFalse() {
        assertFalse(service.hasDiagonalSequence(null));
    }

    @Test
    void hasDiagonalSequence_WithEmptyMatrix_ReturnsFalse() {
        assertFalse(service.hasDiagonalSequence(new char[0][0]));
    }

    @Test
    void hasDiagonalSequence_WithInvalidSize_ReturnsFalse() {
        char[][] matrix = {
            {'A', 'T'},
            {'C', 'G'}
        };
        assertFalse(service.hasDiagonalSequence(matrix));
    }

    @Test
    void hasDiagonalSequence_WithSpaces_ReturnsFalse() {
        char[][] matrix = {
            {'A', 'T', 'G', 'C'},
            {'C', ' ', 'T', 'G'},
            {'G', 'C', ' ', 'T'},
            {'C', 'G', 'T', 'A'}
        };
        assertFalse(service.hasDiagonalSequence(matrix));
    }
}