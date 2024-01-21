package ma.emsi.salleservice.dao;

import ma.emsi.salleservice.bean.Category;
import ma.emsi.salleservice.bean.Salle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SalleDaoTest {

    @Autowired
    private SalleDao salleDao;

    @Test
    public void SalleDao_Save_ReturnsSavedSalle() {
        Category category = Category.builder()
                .name("Reunion")
                .build();
        Salle salle = Salle.builder()
                .code("A1")
                .category(category)
                .capacity(12)
                .build();

        Salle savedSalle = salleDao.save(salle);

        Assertions.assertNotNull(savedSalle);
    }

    @Test
    public void SalleDao_FindAll_ReturnsMoreThanOneSalle() {
        Category category = Category.builder()
                .name("Reunion")
                .build();
        Salle salle1 = Salle.builder().code("A1").capacity(12).build();
        Salle salle2 = Salle.builder().code("A2").capacity(12).build();

        salleDao.save(salle1);
        salleDao.save(salle2);

        List<Salle> salleList = salleDao.findAll();

        Assertions.assertNotNull(salleList);
        Assertions.assertEquals(2, salleList.size());

    }

    @Test
    public void SalleDao_FindById_ReturnsSalle() {
        Salle salle = Salle.builder().code("A2").capacity(12).build();

        salleDao.save(salle);

        Salle returnedSalle = salleDao.findById(salle.getId()).get();

        Assertions.assertNotNull(returnedSalle);
    }

    @Test
    public void SalleDao_DeleteById_ReturnsNull() {
        Salle salle = Salle.builder().code("A2").capacity(12).build();

        salleDao.save(salle);

        salleDao.deleteById(salle.getId());

        Salle returnedSalle = salleDao.findById(salle.getId()).orElse(null);

        Assertions.assertEquals(null, returnedSalle);
    }
}
