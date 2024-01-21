package ma.emsi.reservationservice.clients;

import ma.emsi.reservationservice.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientRestClient {
    @GetMapping("/api/clients")
    List<Client> findAllClients();

    @GetMapping("/api/clients/id/{id}")
    Client findClientById(@PathVariable Long id);
}
