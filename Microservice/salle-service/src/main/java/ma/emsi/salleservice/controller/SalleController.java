package ma.emsi.salleservice.controller;

import ma.emsi.salleservice.bean.Salle;
import ma.emsi.salleservice.service.SalleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salles")
public class SalleController {

    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    @GetMapping
    public ResponseEntity<List<Salle>> findAll() {
        List<Salle> salles = salleService.findAll();
        return new ResponseEntity<>(salles, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Salle> findById(@PathVariable Long id) {
        try {
            Salle salle = salleService.findById(id);
            return new ResponseEntity<>(salle, HttpStatus.OK);
        } catch (Exception e) {
            // You can customize the exception handling logic based on your requirements
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Salle> save(@RequestBody Salle entity) {
        Salle savedSalle = salleService.save(entity);
        return new ResponseEntity<>(savedSalle, HttpStatus.CREATED);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            salleService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // You can customize the exception handling logic based on your requirements
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
