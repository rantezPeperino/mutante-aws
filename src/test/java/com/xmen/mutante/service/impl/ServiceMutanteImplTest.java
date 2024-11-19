package com.xmen.mutante;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.xmen.mutante.entity.AdnSecuencia;
import com.xmen.mutante.exception.InvalidMutanteException;
import com.xmen.mutante.repository.ADNRepositorio;
import com.xmen.mutante.service.ServiceSecuenciaAdn;
import com.xmen.mutante.service.impl.ServiceMutanteImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceMutanteImplTest {

    @Mock
    private ServiceSecuenciaAdn serviceSecuenciaAdn;

    @Mock
    private ADNRepositorio adnRepositorio;

    @InjectMocks
    private ServiceMutanteImpl serviceMutante;

    @Test
    void testIsMutant_ConSecuenciaHorizontal() throws InvalidMutanteException {
        String[] dna = {
            "ATGCGA",
            "AAAAAAC",  // Secuencia horizontal AAAA
            "ATAGTA",
            "CCCGGG",
            "TCACTG",
            "TCACTG"
        };
        
        when(serviceSecuenciaAdn.isMutant(dna)).thenReturn(true);
        when(adnRepositorio.save(any(AdnSecuencia.class))).thenReturn(new AdnSecuencia(dna, 1));
        
        assertTrue(serviceMutante.isMutant(dna));
        verify(adnRepositorio).save(any(AdnSecuencia.class));
    }

    @Test
    void testIsMutant_ConSecuenciaVertical() throws InvalidMutanteException {
        String[] dna = {
            "ATGCGA",
            "ATAGTA",
            "ATGGTA",
            "ATCGGG",
            "ACATTG",
            "TCACTG"
        };
        
        when(serviceSecuenciaAdn.isMutant(dna)).thenReturn(true);
        when(adnRepositorio.save(any(AdnSecuencia.class))).thenReturn(new AdnSecuencia(dna, 1));
        
        assertTrue(serviceMutante.isMutant(dna));
        verify(adnRepositorio).save(any(AdnSecuencia.class));
    }

    @Test
    void testIsMutant_ConSecuenciaDiagonal() throws InvalidMutanteException {
        String[] dna = {
            "ATGCGA",
            "CAGTAC",
            "TTATGT",
            "AGAAGG",
            "CCCTTA",
            "TCACTG"
        };
        
        when(serviceSecuenciaAdn.isMutant(dna)).thenReturn(true);
        when(adnRepositorio.save(any(AdnSecuencia.class))).thenReturn(new AdnSecuencia(dna, 1));
        
        assertTrue(serviceMutante.isMutant(dna));
        verify(adnRepositorio).save(any(AdnSecuencia.class));
    }

    @Test
    void testIsMutant_ConSecuenciaDiagonalInversa() throws InvalidMutanteException {
        String[] dna = {
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAATG",
            "CCCGTA",
            "TCACTG"
        };
        
        when(serviceSecuenciaAdn.isMutant(dna)).thenReturn(true);
        when(adnRepositorio.save(any(AdnSecuencia.class))).thenReturn(new AdnSecuencia(dna, 1));
        
        assertTrue(serviceMutante.isMutant(dna));
        verify(adnRepositorio).save(any(AdnSecuencia.class));
    }

    @Test
    void testIsMutant_MultiplesSecuencias() throws InvalidMutanteException {
        String[] dna = {
            "ATGCGA",
            "AAAATC",
            "ATATGT",
            "ATATGG",
            "ATCCTA",
            "ATACTG"
        };
        
        when(serviceSecuenciaAdn.isMutant(dna)).thenReturn(true);
        when(adnRepositorio.save(any(AdnSecuencia.class))).thenReturn(new AdnSecuencia(dna, 1));
        
        assertTrue(serviceMutante.isMutant(dna));
        verify(adnRepositorio).save(any(AdnSecuencia.class));
    }

    @Test
    void testIsNotMutant_SinSecuencias() throws InvalidMutanteException {
        String[] dna = {
            "ATGCGA",
            "CAGTGC",
            "TTATTT",
            "AGACGG",
            "GCGTCA",
            "TCACTG"
        };
        
        when(serviceSecuenciaAdn.isMutant(dna)).thenReturn(false);
        when(adnRepositorio.save(any(AdnSecuencia.class))).thenReturn(new AdnSecuencia(dna, 0));
        
        assertFalse(serviceMutante.isMutant(dna));
        verify(adnRepositorio).save(any(AdnSecuencia.class));
    }

    @Test
    void testIsMutant_MatrizNull() {
        when(serviceSecuenciaAdn.isMutant(null)).thenThrow(new InvalidMutanteException("La secuencia ADN no puede ser null o vacia"));
        
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            serviceMutante.isMutant(null);
        });
    
        String expectedMessage = "La secuencia ADN no puede ser null o vacia";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testIsMutant_MatrizVacia() {
        String[] dna = {};
        
        when(serviceSecuenciaAdn.isMutant(dna)).thenThrow(new InvalidMutanteException("La secuencia ADN no puede ser null o vacia"));
        
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            serviceMutante.isMutant(dna);
        });

        String expectedMessage = "La secuencia ADN no puede ser null o vacia";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testIsMutant_ErrorBaseDatos() {
        String[] dna = {
            "ATGCGA",
            "CAGTGC",
            "TTATTT",
            "AGACGG",
            "GCGTCA",
            "TCACTG"
        };
        
        when(serviceSecuenciaAdn.isMutant(dna)).thenReturn(true);
        when(adnRepositorio.save(any(AdnSecuencia.class))).thenThrow(new DataIntegrityViolationException("Error al guardar"));
        
        Exception exception = assertThrows(InvalidMutanteException.class, () -> {
            serviceMutante.isMutant(dna);
        });
    
        String expectedMessage = "La secuencia de ADN existe en la base de datos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testIsMutant_CasoLimite_MatrizMinima4x4() throws InvalidMutanteException {
        String[] dna = {
            "AAAA",
            "ATCG",
            "ATCG",
            "ATCG"
        };
        
        when(serviceSecuenciaAdn.isMutant(dna)).thenReturn(true);
        when(adnRepositorio.save(any(AdnSecuencia.class))).thenReturn(new AdnSecuencia(dna, 1));
        
        assertTrue(serviceMutante.isMutant(dna));
        verify(adnRepositorio).save(any(AdnSecuencia.class));
    }

    @Test
    void testIsMutant_CasoLimite_MatrizGrande() throws InvalidMutanteException {
        String[] dna = {
            "ATGCGAAAA",
            "CAGTGCTTT",
            "TTATTTGGG",
            "AGACGGCCC",
            "GCGTCAAAA",
            "TCACTGGGG",
            "TCACTGCCC",
            "TCACTGAAA",
            "TCACTGTTT"
        };
        
        when(serviceSecuenciaAdn.isMutant(dna)).thenReturn(true);
        when(adnRepositorio.save(any(AdnSecuencia.class))).thenReturn(new AdnSecuencia(dna, 1));
        
        assertTrue(serviceMutante.isMutant(dna));
        verify(adnRepositorio).save(any(AdnSecuencia.class));
    }
}