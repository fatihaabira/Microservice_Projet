package ma.emsi.reservationservice.clients;

import ma.emsi.reservationservice.model.Salle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("SALLE-SERVICE")
public interface SalleRestClient {

    @GetMapping("/api/salles")
    List<Salle> findAllSalles();

    @GetMapping("/api/salles/id/{id}")
    Salle findSalleById(@PathVariable Long id);
}
