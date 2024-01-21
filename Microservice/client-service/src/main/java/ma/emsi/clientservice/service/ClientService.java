package ma.emsi.clientservice.service;

import ma.emsi.clientservice.bean.Client;
import ma.emsi.clientservice.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    //injecter Dao dans le service
    @Autowired
    ClientDao cd;

    public List<Client> findAll() {
        return cd.findAll();
    }

    public int save(Client client) {
        if (client == null) return -1;
        else {
            cd.save(client);
            return 1;
        }
    }

    public Client findById(Long id) throws Exception {
        Optional<Client> result = cd.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new Exception("Client does not exist, invalid id");
    }

    public void deleteById(Long aLong) {
        cd.deleteById(aLong);
    }

    public void deleteAll() {
        cd.deleteAll();
    }
}
