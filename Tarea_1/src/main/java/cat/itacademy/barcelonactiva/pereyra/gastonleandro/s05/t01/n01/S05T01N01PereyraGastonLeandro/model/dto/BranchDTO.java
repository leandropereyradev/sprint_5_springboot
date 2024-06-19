package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public class BranchDTO {
    private Integer pk_BranchID;
    private String branchName;
    private String branchCountry;
    private String branchType;

    private static final List<String> euCountries = Arrays.asList(
            "austria", "belgium", "bulgaria", "croatia", "cyprus",
            "czech republic", "denmark", "estonia", "finland",
            "france", "germany", "greece", "hungary", "ireland",
            "italy", "latvia", "lithuania", "luxembourg", "malta",
            "netherlands", "poland", "portugal", "romania",
            "slovakia", "slovenia", "spain", "sweden"
    );

    public BranchDTO(Integer pk_BranchID, String branchName, String branchCountry) {
        String name = branchName.toLowerCase().trim();
        String country = branchCountry.toLowerCase().trim();

        this.pk_BranchID = pk_BranchID;
        this.branchName = name;
        this.branchCountry = country;
        this.branchType = euCountries.contains(country) ? "EU" : "Non-EU";
    }

}
