package ma.emsi.reservationservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SecondaryRow;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salle {
    private Long id;
    private String code;
    private int capacity;

    private Category category;
}

