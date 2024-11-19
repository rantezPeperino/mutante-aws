package com.xmen.mutante;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xmen.mutante.entity.AdnSecuencia;
import com.xmen.mutante.repository.ADNRepositorio;


@ExtendWith(MockitoExtension.class)
public class AdnSecuenciaTest {

    @Mock
    private ADNRepositorio adnRepositorio;

    private AdnSecuencia adnSecuencia;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        // Inicializar datos de prueba
        String[] secuenciaADN = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        adnSecuencia = new AdnSecuencia(secuenciaADN, 1);
    }

    @Test
    void testConstructorVacio() {
        AdnSecuencia adn = new AdnSecuencia();
        assertNotNull(adn);
        assertArrayEquals(new String[0], adn.getAdnSequence());
        assertNull(adn.getIsMutante());
    }

    @Test
    void testConstructorConParametros() {
        String[] secuenciaADN = {"ATGCGA", "CAGTGC"};
        AdnSecuencia adn = new AdnSecuencia(secuenciaADN, 1);
        
        assertNotNull(adn);
        assertArrayEquals(secuenciaADN, adn.getAdnSequence());
        assertEquals(1, adn.getIsMutante());
    }

    @Test
    void testSetAndGetId() {
        Long id = 1L;
        adnSecuencia.setId(id);
        assertEquals(id, adnSecuencia.getId());
    }

    @Test
    void testSetAndGetAdnSequence() {
        String[] nuevaSecuencia = {"GGGGCC", "ATATAT"};
        adnSecuencia.setAdnSequence(nuevaSecuencia);
        assertArrayEquals(nuevaSecuencia, adnSecuencia.getAdnSequence());
    }

    @Test
    void testSetAndGetIsMutante() {
        Integer nuevoValor = 0;
        adnSecuencia.setIsMutante(nuevoValor);
        assertEquals(nuevoValor, adnSecuencia.getIsMutante());
    }

    @Test
    void testFindByIsMutante() {
        // Preparar datos de prueba
        List<AdnSecuencia> mutantes = Arrays.asList(
            new AdnSecuencia(new String[]{"AAAA", "TTTT"}, 1),
            new AdnSecuencia(new String[]{"GGGG", "CCCC"}, 1)
        );
        
        // Configurar el mock
        when(adnRepositorio.findByIsMutante(1)).thenReturn(mutantes);
        
        // Ejecutar la prueba
        List<AdnSecuencia> resultado = adnRepositorio.findByIsMutante(1);
        
        // Verificar resultados
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(adnRepositorio).findByIsMutante(1);
    }

    @Test
    void testCountByIsMutante() {
        // Configurar el mock
        when(adnRepositorio.countByIsMutante(1)).thenReturn(5);
        when(adnRepositorio.countByIsMutante(0)).thenReturn(3);
        
        // Ejecutar pruebas
        Integer mutantes = adnRepositorio.countByIsMutante(1);
        Integer noMutantes = adnRepositorio.countByIsMutante(0);
        
        // Verificar resultados
        assertEquals(5, mutantes);
        assertEquals(3, noMutantes);
        verify(adnRepositorio).countByIsMutante(1);
        verify(adnRepositorio).countByIsMutante(0);
    }

    @Test
    void testAdnSequenceNulo() {
        AdnSecuencia adn = new AdnSecuencia();
        assertArrayEquals(new String[0], adn.getAdnSequence(), 
            "Debería devolver un array vacío cuando adnSequence es nulo");
    }

    @Test
    void testPersistenciaAdnSecuencia() {
        // Preparar datos de prueba
        String[] secuencia = {"ATGCGA", "CAGTGC"};
        AdnSecuencia adn = new AdnSecuencia(secuencia, 1);
        
        // Configurar mock para simular guardado
        when(adnRepositorio.save(any(AdnSecuencia.class))).thenReturn(adn);
        
        // Ejecutar prueba
        AdnSecuencia guardado = adnRepositorio.save(adn);
        
        // Verificar resultados
        assertNotNull(guardado);
        assertArrayEquals(secuencia, guardado.getAdnSequence());
        assertEquals(1, guardado.getIsMutante());
        verify(adnRepositorio).save(adn);
    }



}
