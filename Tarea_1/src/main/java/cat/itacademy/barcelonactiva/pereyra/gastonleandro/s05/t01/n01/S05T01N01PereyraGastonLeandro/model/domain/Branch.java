package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("pk_BranchID")
    private Integer pk_BranchID;

    @JsonProperty("branchName")
    private String branchName;

    @JsonProperty("branchCountry")
    private String branchCountry;
}
