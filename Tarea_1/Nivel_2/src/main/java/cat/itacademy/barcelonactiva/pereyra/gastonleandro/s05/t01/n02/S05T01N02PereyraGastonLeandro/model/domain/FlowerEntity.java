package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class FlowerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pk_FlowerID;

    private String flowerName;

    private String flowerCountry;
}
