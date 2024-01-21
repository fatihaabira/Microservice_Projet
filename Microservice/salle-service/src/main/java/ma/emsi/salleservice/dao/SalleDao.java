package ma.emsi.salleservice.dao;

import ma.emsi.salleservice.bean.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleDao extends JpaRepository<Salle, Long> {
}
