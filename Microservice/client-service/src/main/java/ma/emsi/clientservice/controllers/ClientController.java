package ma.emsi.clientservice.controllers;


import ma.emsi.clientservice.bean.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ma.emsi.clientservice.service.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService cs;

    public ClientController(ClientService clientService) {
        this.cs = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        List<Client> clients = cs.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        try {
            Client client = cs.findById(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Client client) {
        cs.save(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        cs.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        cs.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
