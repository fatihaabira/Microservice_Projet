package ma.emsi.salleservice.service;

import ma.emsi.salleservice.bean.Salle;
import ma.emsi.salleservice.dao.SalleDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalleService {

    private SalleDao salleDao;

    public SalleService(SalleDao salleDao) {
        this.salleDao = salleDao;
    }

    public List<Salle> findAll() {
        return salleDao.findAll();
    }

    public Salle save(Salle entity) {
        return salleDao.save(entity);
    }

    public Salle findById(Long id) throws Exception {
        Optional<Salle> result = salleDao.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new Exception("Salle does not exist, invalid id");
    }

    public void deleteById(Long aLong) {
        salleDao.deleteById(aLong);
    }
}
