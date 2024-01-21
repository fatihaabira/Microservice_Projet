package ma.emsi.reservationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.emsi.reservationservice.bean.Reservation;
import ma.emsi.reservationservice.service.ReservationService;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ReservationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReservationControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    private final ObjectMapper objectMapper;

    @Autowired
    public ReservationControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void testFindAll() throws Exception {

        List<Reservation> reservations = Arrays.asList(
                new Reservation(1L, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), 1L, null, 1L, null),
                new Reservation(2L, LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(16, 0), 2L, null, 2L, null)
        );

        when(reservationService.findAll()).thenReturn(reservations);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(reservations)));
    }

    @Test
    void testSave() throws Exception {

        Reservation reservationToSave = new Reservation(null, LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(12, 0), 1L, null, 1L, null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationToSave)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testFindById() throws Exception {

        Long reservationId = 1L;
        Reservation reservation = new Reservation(reservationId, LocalDate.now(), LocalTime.of(14, 0), LocalTime.of(16, 0), 2L, null, 2L, null);

        when(reservationService.findById(reservationId)).thenReturn(reservation);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/reservations/id/{id}", reservationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(reservation)));
    }

    @Test
    void testDeleteById() throws Exception {

        Long reservationId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/reservations/id/{id}", reservationId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
