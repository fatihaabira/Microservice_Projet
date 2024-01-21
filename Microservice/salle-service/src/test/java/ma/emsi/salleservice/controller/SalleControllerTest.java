package ma.emsi.salleservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.emsi.salleservice.bean.Salle;
import ma.emsi.salleservice.service.SalleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@WebMvcTest(controllers = SalleController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SalleControllerTest {


    private final MockMvc mockMvc;

    @MockBean
    private SalleService salleService;


    private final ObjectMapper objectMapper;

    @Autowired
    public SalleControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void SalleController_testFindAll() throws Exception {
        // Given
        List<Salle> salles = Arrays.asList(
                Salle.builder().code("A1").capacity(12).build(),
                Salle.builder().code("A2").capacity(12).build()
        );

        when(salleService.findAll()).thenReturn(salles);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/salles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(salles)));
    }

    @Test
    void testFindById() throws Exception {
        // Given
        Long salleId = 1L;
        Salle salle = Salle.builder().code("A1").capacity(12).build();

        when(salleService.findById(salleId)).thenReturn(salle);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/salles/id/{id}", salleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(salle)));
    }

    @Test
    void testSaveSalle() throws Exception {
        // Given
        Salle salleToSave = Salle.builder().code("A1").capacity(12).build();
        Salle salleToExpect = Salle.builder().code("A1").capacity(12).build();

        when(salleService.save(any(Salle.class))).thenReturn(salleToExpect);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/salles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salleToSave)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(salleToExpect)));
    }

    @Test
    void testDeleteById() throws Exception {
        // Given
        Long salleId = 1L;

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/salles/id/{id}", salleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
