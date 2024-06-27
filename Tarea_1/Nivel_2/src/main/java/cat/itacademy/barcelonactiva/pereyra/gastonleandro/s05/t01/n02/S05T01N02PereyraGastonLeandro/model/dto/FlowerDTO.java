package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public class FlowerDTO {
    private Integer pk_FlowerID;
    private String flowerName;
    private String flowerCountry;
    private String flowerType;

    private static final List<String> euCountries = Arrays.asList(
            "austria", "belgium", "bulgaria", "croatia", "cyprus",
            "czech republic", "denmark", "estonia", "finland",
            "france", "germany", "greece", "hungary", "ireland",
            "italy", "latvia", "lithuania", "luxembourg", "malta",
            "netherlands", "poland", "portugal", "romania",
            "slovakia", "slovenia", "spain", "sweden"
    );

    public FlowerDTO(Integer pk_FlowerID, String flowerName, String flowerCountry) {
        String name = flowerName.toLowerCase().trim();
        String country = flowerCountry.toLowerCase().trim();

        this.pk_FlowerID = pk_FlowerID;
        this.flowerName = name;
        this.flowerCountry = country;
        this.flowerType = euCountries.contains(country) ? "EU" : "Non-EU";
    }

}
