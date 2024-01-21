package ma.emsi.clientservice.service;

import ma.emsi.clientservice.bean.Client;
import ma.emsi.clientservice.dao.ClientDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    @Mock
    private ClientDao clientDao;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Given
        List<Client> clients = Arrays.asList(
                new Client(1L, "John", "Doe", "john@example.com", "123456789"),
                new Client(2L, "Jane", "Doe", "jane@example.com", "987654321")
        );

        when(clientDao.findAll()).thenReturn(clients);

        // When
        List<Client> result = clientService.findAll();

        // Then
        Assertions.assertEquals(clients.size(), result.size());
        Assertions.assertTrue(result.containsAll(clients));
    }

    @Test
    void testSave() {
        // Given
        Client clientToSave = new Client(null, "John", "Doe", "john@example.com", "123456789");
        Client savedClient = new Client(1L, "John", "Doe", "john@example.com", "123456789");

        when(clientDao.save(any(Client.class))).thenReturn(savedClient);

        // When
        int result = clientService.save(clientToSave);

        // Then
        Assertions.assertEquals(1, result);
    }

    @Test
    void testFindById() throws Exception {
        // Given
        Long clientId = 1L;
        Client client = new Client(clientId, "John", "Doe", "john@example.com", "123456789");

        when(clientDao.findById(clientId)).thenReturn(Optional.of(client));

        // When
        Client result = clientService.findById(clientId);

        // Then
        Assertions.assertEquals(client, result);
    }

    @Test
    void testDeleteById() {
        // Given
        Long clientId = 1L;

        // When
        clientService.deleteById(clientId);

        // Then
        verify(clientDao, times(1)).deleteById(clientId);
    }

    @Test
    void testDeleteAll() {
        // When
        clientService.deleteAll();

        // Then
        verify(clientDao, times(1)).deleteAll();
    }
}
