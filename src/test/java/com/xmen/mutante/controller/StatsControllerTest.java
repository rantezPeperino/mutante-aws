package com.xmen.mutante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.xmen.mutante.controller.StatsController;
import com.xmen.mutante.model.Stats;
import com.xmen.mutante.service.StatsService;

@ExtendWith(MockitoExtension.class)
public class StatsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StatsService statsService;

    @InjectMocks
    private StatsController statsController;

    private Stats mockStats;

    @BeforeEach
    void setUp() {
        mockStats = new Stats(100, 40, 0.4f);
        mockMvc = MockMvcBuilders
                .standaloneSetup(statsController)
                .build();
    }

    @Test
    void getStats_ShouldReturnStats() throws Exception {
        // Arrange
        when(statsService.calculateStats()).thenReturn(mockStats);

        // Act & Assert
        mockMvc.perform(get("/stats/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutante_dna").value(40))
                .andExpect(jsonPath("$.count_human_dna").value(100))
                .andExpect(jsonPath("$.ratio").value(0.4));
    }

    @Test
    void getStats_WhenServiceReturnsNull_ShouldReturnNoContent() throws Exception {
        // Arrange
        when(statsService.calculateStats()).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/stats/"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getStats_WhenServiceThrowsException_ShouldReturn500() throws Exception {
        // Arrange
        when(statsService.calculateStats()).thenThrow(new RuntimeException("Error calculando estadisticas"));

        // Act & Assert
        mockMvc.perform(get("/stats/"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getStats_WithZeroHumans_ShouldHandleInfiniteRatio() throws Exception {
        // Arrange
        Stats zeroHumansStats = new Stats(0, 40, Float.POSITIVE_INFINITY);
        when(statsService.calculateStats()).thenReturn(zeroHumansStats);

        // Act & Assert
        mockMvc.perform(get("/stats/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count_mutante_dna").value(40))
                .andExpect(jsonPath("$.count_human_dna").value(0));
    }
}
