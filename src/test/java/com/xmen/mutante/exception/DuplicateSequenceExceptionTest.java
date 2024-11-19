package com.xmen.mutante.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class DuplicateSequenceExceptionTest {

     @Test
    public void testCrearExcepcionConMensaje() {
        // Arrange
        String mensajeError = "Secuencia duplicada encontrada";

        // Act
        DuplicateSequenceException excepcion = new DuplicateSequenceException(mensajeError);

        // Assert
        assertEquals(mensajeError, excepcion.getMessage(), 
            "El mensaje de la excepción debería coincidir con el mensaje proporcionado");
    }

    @Test
    public void testExcepcionEsRuntimeException() {
        // Arrange & Act
        DuplicateSequenceException excepcion = new DuplicateSequenceException("Test");

        // Assert
        assertTrue(excepcion instanceof RuntimeException, 
            "DuplicateSequenceException debe ser una subclase de RuntimeException");
    }


    @Test
    public void testMensajeVacio() {
        // Arrange & Act
        DuplicateSequenceException excepcion = new DuplicateSequenceException("");

        // Assert
        assertEquals("", excepcion.getMessage(),
            "La excepción debería aceptar un mensaje vacío");
    }

    @Test
    public void testMensajeNulo() {
        // Arrange & Act
        DuplicateSequenceException excepcion = new DuplicateSequenceException(null);

        // Assert
        assertNull(excepcion.getMessage(),
            "La excepción debería aceptar un mensaje nulo");
    }

    @Test
    public void testStackTraceNoEsNulo() {
        // Arrange
        DuplicateSequenceException excepcion = new DuplicateSequenceException("Test");

        // Act & Assert
        assertNotNull(excepcion.getStackTrace(),
            "El stack trace no debería ser nulo");
        assertTrue(excepcion.getStackTrace().length > 0,
            "El stack trace debería contener elementos");
    }

}
