package ma.emsi.reservationservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class Client {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
}