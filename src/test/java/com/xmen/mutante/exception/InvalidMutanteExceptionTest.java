package com.xmen.mutante.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class InvalidMutanteExceptionTest {

    @Test
    public void testCreacionExcepcionConMensaje() {
        // Arrange
        String mensajeError = "ADN mutante inválido";
        
        // Act
        InvalidMutanteException excepcion = new InvalidMutanteException(mensajeError);
        
        // Assert
        assertEquals(mensajeError, excepcion.getMessage(), 
            "El mensaje de la excepción debe coincidir con el mensaje proporcionado");
    }
    
    @Test
    public void testVerificarHerenciaRuntimeException() {
        // Arrange & Act
        InvalidMutanteException excepcion = new InvalidMutanteException("Test mutante");
        
        // Assert
        assertTrue(excepcion instanceof RuntimeException, 
            "InvalidMutanteException debe ser una subclase de RuntimeException");
    }
    
    
    @Test
    public void testManejoMensajeVacio() {
        // Arrange & Act
        InvalidMutanteException excepcion = new InvalidMutanteException("");
        
        // Assert
        assertEquals("", excepcion.getMessage(),
            "La excepción debe manejar correctamente un mensaje vacío");
    }
    
    @Test
    public void testManejoMensajeNulo() {
        // Arrange & Act
        InvalidMutanteException excepcion = new InvalidMutanteException(null);
        
        // Assert
        assertNull(excepcion.getMessage(),
            "La excepción debe manejar correctamente un mensaje nulo");
    }
    
    @Test
    public void testGeneracionStackTrace() {
        // Arrange
        InvalidMutanteException excepcion = new InvalidMutanteException("Error de validación");
        
        // Act & Assert
        assertNotNull(excepcion.getStackTrace(),
            "El stack trace no debe ser nulo");
        assertTrue(excepcion.getStackTrace().length > 0,
            "El stack trace debe contener al menos un elemento");
    }





}
