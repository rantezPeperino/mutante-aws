package com.xmen.mutante;



import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xmen.mutante.model.Stats;
import com.xmen.mutante.repository.ADNRepositorio;
import com.xmen.mutante.service.impl.StatsServiceImpl;
import com.xmen.mutante.utils.Constant;

@ExtendWith(MockitoExtension.class)
public class StatsServiceImplTest {

    @Mock
    private ADNRepositorio adnRepositorio;

    @InjectMocks
    private StatsServiceImpl statsService;

    @Test
    void testCalculateStats_CasoNormal() {
        // Configurar el comportamiento del mock
        when(adnRepositorio.countByIsMutante(Constant.UNO)).thenReturn(40);
        when(adnRepositorio.countByIsMutante(Constant.CERO)).thenReturn(100);

        // Ejecutar el método a probar
        Stats stats = statsService.calculateStats();

        // Verificar resultados
        assertEquals(100, stats.getCount_human_dna());
        assertEquals(40, stats.getCount_mutante_dna());
        assertEquals(0.4f, stats.getRatio(), 0.001);

        // Verificar que los métodos del repositorio fueron llamados
        verify(adnRepositorio).countByIsMutante(Constant.UNO);
        verify(adnRepositorio).countByIsMutante(Constant.CERO);
    }

    @Test
    void testCalculateStats_SinHumanos() {
        // Configurar el comportamiento del mock
        when(adnRepositorio.countByIsMutante(Constant.UNO)).thenReturn(40);
        when(adnRepositorio.countByIsMutante(Constant.CERO)).thenReturn(0);

        // Ejecutar el método a probar
        Stats stats = statsService.calculateStats();

        // Verificar resultados
        assertEquals(0, stats.getCount_human_dna());
        assertEquals(40, stats.getCount_mutante_dna());
        assertEquals(0.0f, stats.getRatio(), 0.001);

        // Verificar que los métodos del repositorio fueron llamados
        verify(adnRepositorio).countByIsMutante(Constant.UNO);
        verify(adnRepositorio).countByIsMutante(Constant.CERO);
    }

    @Test
    void testCalculateStats_SinMutantes() {
        // Configurar el comportamiento del mock
        when(adnRepositorio.countByIsMutante(Constant.UNO)).thenReturn(0);
        when(adnRepositorio.countByIsMutante(Constant.CERO)).thenReturn(100);

        // Ejecutar el método a probar
        Stats stats = statsService.calculateStats();

        // Verificar resultados
        assertEquals(100, stats.getCount_human_dna());
        assertEquals(0, stats.getCount_mutante_dna());
        assertEquals(0.0f, stats.getRatio(), 0.001);

        // Verificar que los métodos del repositorio fueron llamados
        verify(adnRepositorio).countByIsMutante(Constant.UNO);
        verify(adnRepositorio).countByIsMutante(Constant.CERO);
    }

    @Test
    void testCalculateStats_SinRegistros() {
        // Configurar el comportamiento del mock
        when(adnRepositorio.countByIsMutante(Constant.UNO)).thenReturn(0);
        when(adnRepositorio.countByIsMutante(Constant.CERO)).thenReturn(0);

        // Ejecutar el método a probar
        Stats stats = statsService.calculateStats();

        // Verificar resultados
        assertEquals(0, stats.getCount_human_dna());
        assertEquals(0, stats.getCount_mutante_dna());
        assertEquals(0.0f, stats.getRatio(), 0.001);

        // Verificar que los métodos del repositorio fueron llamados
        verify(adnRepositorio).countByIsMutante(Constant.UNO);
        verify(adnRepositorio).countByIsMutante(Constant.CERO);
    }

    @Test
    void testCalculateStats_MasMutantesQueHumanos() {
        // Configurar el comportamiento del mock
        when(adnRepositorio.countByIsMutante(Constant.UNO)).thenReturn(100);
        when(adnRepositorio.countByIsMutante(Constant.CERO)).thenReturn(40);

        // Ejecutar el método a probar
        Stats stats = statsService.calculateStats();

        // Verificar resultados
        assertEquals(40, stats.getCount_human_dna());
        assertEquals(100, stats.getCount_mutante_dna());
        assertEquals(2.5f, stats.getRatio(), 0.001);

        // Verificar que los métodos del repositorio fueron llamados
        verify(adnRepositorio).countByIsMutante(Constant.UNO);
        verify(adnRepositorio).countByIsMutante(Constant.CERO);
    }

    @Test
    void testCalculateStats_NumerosGrandes() {
        // Configurar el comportamiento del mock
        when(adnRepositorio.countByIsMutante(Constant.UNO)).thenReturn(1000000);
        when(adnRepositorio.countByIsMutante(Constant.CERO)).thenReturn(2000000);

        // Ejecutar el método a probar
        Stats stats = statsService.calculateStats();

        // Verificar resultados
        assertEquals(2000000, stats.getCount_human_dna());
        assertEquals(1000000, stats.getCount_mutante_dna());
        assertEquals(0.5f, stats.getRatio(), 0.001);

        // Verificar que los métodos del repositorio fueron llamados
        verify(adnRepositorio).countByIsMutante(Constant.UNO);
        verify(adnRepositorio).countByIsMutante(Constant.CERO);
    }
}