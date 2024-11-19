package com.xmen.mutante;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xmen.mutante.exception.InvalidMutanteException;
import com.xmen.mutante.service.ServiceDiagonal;
import com.xmen.mutante.service.ServiceHorizontal;
import com.xmen.mutante.service.ServiceVertical;
import com.xmen.mutante.service.impl.ServiceSecuenciaAdnImpl;

@ExtendWith(MockitoExtension.class)
class ServiceSecuenciaAdnImplTest {

    @Mock(lenient = true)
    private ServiceHorizontal serviceHorizontal;

    @Mock(lenient = true)
    private ServiceDiagonal serviceDiagonal;

    @Mock(lenient = true)
    private ServiceVertical serviceVertical;
    
    @InjectMocks
    private ServiceSecuenciaAdnImpl serviceSecuenciaAdn;

    private String[] dnaMatrix;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        dnaMatrix = new String[]{
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
        };
    }

    @Test
    void isMutant_WithDiagonalSequence_ReturnsTrue() {
        // Arrange
        when(serviceDiagonal.hasDiagonalSequence(any())).thenReturn(true);
        when(serviceHorizontal.hasHorizontalSequence(any())).thenReturn(false);
        when(serviceVertical.hasVerticalSequence(any())).thenReturn(false);

        // Act
        boolean result = serviceSecuenciaAdn.isMutant(dnaMatrix);

        // Assert
        assertTrue(result);
    }

    @Test
    void isMutant_WithHorizontalSequence_ReturnsTrue() {
        // Arrange
        when(serviceDiagonal.hasDiagonalSequence(any())).thenReturn(false);
        when(serviceHorizontal.hasHorizontalSequence(any())).thenReturn(true);
        when(serviceVertical.hasVerticalSequence(any())).thenReturn(false);

        // Act
        boolean result = serviceSecuenciaAdn.isMutant(dnaMatrix);

        // Assert
        assertTrue(result);
    }

    @Test
    void isMutant_WithVerticalSequence_ReturnsTrue() {
        // Arrange
        when(serviceDiagonal.hasDiagonalSequence(any())).thenReturn(false);
        when(serviceHorizontal.hasHorizontalSequence(any())).thenReturn(false);
        when(serviceVertical.hasVerticalSequence(any())).thenReturn(true);

        // Act
        boolean result = serviceSecuenciaAdn.isMutant(dnaMatrix);

        // Assert
        assertTrue(result);
    }

    @Test
    void isMutant_WithNoSequences_ReturnsFalse() {
        // Arrange
        when(serviceDiagonal.hasDiagonalSequence(any())).thenReturn(false);
        when(serviceHorizontal.hasHorizontalSequence(any())).thenReturn(false);
        when(serviceVertical.hasVerticalSequence(any())).thenReturn(false);

        // Act
        boolean result = serviceSecuenciaAdn.isMutant(dnaMatrix);

        // Assert
        assertFalse(result);
    }

    @Test
    void isMutant_WithAllSequences_ReturnsTrue() {
        // Arrange
        when(serviceDiagonal.hasDiagonalSequence(any())).thenReturn(true);
        when(serviceHorizontal.hasHorizontalSequence(any())).thenReturn(true);
        when(serviceVertical.hasVerticalSequence(any())).thenReturn(true);

        // Act
        boolean result = serviceSecuenciaAdn.isMutant(dnaMatrix);

        // Assert
        assertTrue(result);
    }

    @Test
    void isMutant_WithEmptyDna_ReturnsFalse() {
        // Arrange
        String[] emptyDna = new String[]{};
        when(serviceDiagonal.hasDiagonalSequence(any())).thenReturn(false);
        when(serviceHorizontal.hasHorizontalSequence(any())).thenReturn(false);
        when(serviceVertical.hasVerticalSequence(any())).thenReturn(false);

        // Act
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            serviceSecuenciaAdn.isMutant(emptyDna);
        });

        String expectedMessage = "La secuencia ADN no puede ser null o vacia";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void isMutant_WithInvalidDna_ReturnsFalse() {
        // Arrange
        String[] invalidDna = new String[]{"AT", "CG"};
        when(serviceDiagonal.hasDiagonalSequence(any())).thenReturn(false);
        when(serviceHorizontal.hasHorizontalSequence(any())).thenReturn(false);
        when(serviceVertical.hasVerticalSequence(any())).thenReturn(false);

        // Act
        boolean result = serviceSecuenciaAdn.isMutant(invalidDna);

        // Assert
        assertFalse(result);
    }

    @Test
    void isMutant_WithNullDna_ReturnsFalse() {
        // Arrange
        when(serviceDiagonal.hasDiagonalSequence(any())).thenReturn(false);
        when(serviceHorizontal.hasHorizontalSequence(any())).thenReturn(false);
        when(serviceVertical.hasVerticalSequence(any())).thenReturn(false);

        // Act
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            serviceSecuenciaAdn.isMutant(null);
        });

        String expectedMessage = "La secuencia ADN no puede ser null o vacia";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}


