package ma.emsi.salleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // Pour que le service puisse s'enregistrer dans le registre
public class SalleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalleServiceApplication.class, args);
    }

}
