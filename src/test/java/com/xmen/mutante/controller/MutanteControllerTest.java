package com.xmen.mutante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmen.mutante.controller.MutanteController;
import com.xmen.mutante.model.AdnRequest;
import com.xmen.mutante.service.ServiceMutante;

@ExtendWith(MockitoExtension.class)
public class MutanteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ServiceMutante serviceMutante;

    @InjectMocks
    private MutanteController mutanteController;

    private ObjectMapper objectMapper;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mutanteController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void whenValidMutantDNA_thenReturns200() throws Exception {
        // Preparar datos de prueba
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        AdnRequest request = new AdnRequest();
        request.setDna(dna);

        // Configurar comportamiento del mock
        when(serviceMutante.isMutant(any())).thenReturn(true);

        // Ejecutar y verificar
        mockMvc.perform(post("/mutante/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void whenValidNonMutantDNA_thenReturns403() throws Exception {
        // Preparar datos de prueba
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        AdnRequest request = new AdnRequest();
        request.setDna(dna);

        // Configurar comportamiento del mock
        when(serviceMutante.isMutant(any())).thenReturn(false);

        // Ejecutar y verificar
        mockMvc.perform(post("/mutante/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    void whenInvalidDNAMatrix_thenReturns400() throws Exception {
        // Preparar datos de prueba con matriz inválida
        String[] dna = {"ATG", "CAG", "TTA"}; // Matriz 3x3 
        AdnRequest request = new AdnRequest();
        request.setDna(dna);

        // Ejecutar y verificar
        mockMvc.perform(post("/mutante/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""));
    }

    @Test
    void whenEmptyRequest_thenReturns400() throws Exception {
        // Ejecutar y verificar
        mockMvc.perform(post("/mutante/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenNullDNA_thenReturns400() throws Exception {
        // Preparar request con DNA nulo
        AdnRequest request = new AdnRequest();
        request.setDna(null);

        // Ejecutar y verificar
        mockMvc.perform(post("/mutante/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInvalidDNACharacters_thenReturns400() throws Exception {
        // Preparar datos con caracteres invalidos
        String[] dna = {"ATGCGA","CAGTGC","TTATZG","AGAAGG","CCCCTA","TCACTG"}; // 'Z' no es válido
        AdnRequest request = new AdnRequest();
        request.setDna(dna);

        // Ejecutar y verificar
        mockMvc.perform(post("/mutante/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalido ADN, debe contener A, T, C, G"));
    }

    @Test
    void whenNonSquareMatrix_thenReturns400() throws Exception {
        // Preparar datos con matriz no cuadrada
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA"}; // 6x5
        AdnRequest request = new AdnRequest();
        request.setDna(dna);

        // Ejecutar y verificar
        mockMvc.perform(post("/mutante/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No coincide las dimensiones de la matriz"));
    }

}
