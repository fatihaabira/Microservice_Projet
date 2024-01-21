package ma.emsi.clientservice.repository;


import ma.emsi.clientservice.bean.Client;
import ma.emsi.clientservice.dao.ClientDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ClientDaoTest {

    @Autowired
    private ClientDao clientDao;

    @Test
    public void testSave() {
        Client client = Client.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john@example.com")
                .phone("123456789")
                .build();

        Client savedClient = clientDao.save(client);

        Assertions.assertNotNull(savedClient.getId());
    }

    @Test
    public void testFindAll() {
        Client client1 = Client.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john@example.com")
                .phone("123456789")
                .build();
        Client client2 = Client.builder()
                .firstname("Jane")
                .lastname("Doe")
                .email("jane@example.com")
                .phone("987654321")
                .build();

        clientDao.save(client1);
        clientDao.save(client2);

        List<Client> clientList = clientDao.findAll();

        Assertions.assertNotNull(clientList);
        Assertions.assertEquals(2, clientList.size());
    }

    @Test
    public void testFindById() {
        Client client = Client.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john@example.com")
                .phone("123456789")
                .build();

        clientDao.save(client);

        Optional<Client> returnedClient = clientDao.findById(client.getId());

        Assertions.assertTrue(returnedClient.isPresent());
        Assertions.assertEquals(client.getId(), returnedClient.get().getId());
    }

    @Test
    public void testDeleteById() {
        Client client = Client.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john@example.com")
                .phone("123456789")
                .build();

        clientDao.save(client);

        clientDao.deleteById(client.getId());

        Optional<Client> returnedClient = clientDao.findById(client.getId());

        Assertions.assertFalse(returnedClient.isPresent());
    }
}
