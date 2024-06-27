package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.controller;

import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.model.service.FlowerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flower")
@Slf4j
public class FlowerController {

    @Autowired
    private FlowerServiceImpl flowerService;

    @Operation(summary = "Add a list of flowers", description = "Adds a list of flower objects to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flowers added successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/add")
    public ResponseEntity<List<FlowerDTO>> addFlowers(
            @Parameter(description = "List of FlowerDTO objects to be added") @RequestBody List<FlowerDTO> flowerDTOs) {
        List<FlowerDTO> addedFlowers = flowerService.addFlowers(flowerDTOs);

        return ResponseEntity.ok(addedFlowers);
    }

    @Operation(summary = "Update a flower", description = "Updates the details of an existing flower identified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower updated successfully"),
            @ApiResponse(responseCode = "404", description = "Flower not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<FlowerDTO> updateFlower(
            @Parameter(description = "ID of the flower to be updated") @PathVariable int id,
            @Parameter(description = "Updated FlowerDTO object") @RequestBody FlowerDTO flowerDTO) {
        FlowerDTO updatedFlower = flowerService.updateFlower(id, flowerDTO);

        if (updatedFlower == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.ok(updatedFlower);
    }

    @Operation(summary = "Delete a flower", description = "Deletes a flower from the database identified by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Flower deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Flower not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlower(
            @Parameter(description = "ID of the flower to be deleted") @PathVariable int id) {
        boolean isDeleted = flowerService.deleteFlower(id);

        if (!isDeleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all flowers", description = "Retrieves a list of all flowers from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flowers retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/getAll")
    public ResponseEntity<List<FlowerDTO>> getAllFlowers() {
        List<FlowerDTO> flowers = flowerService.getAllFlowers();

        return ResponseEntity.ok(flowers);
    }

    @Operation(summary = "Get a flower by ID", description = "Retrieves a single flower by its ID from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flower retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Flower not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/getOne/{id}")
    public ResponseEntity<FlowerDTO> getFlowerById(
            @Parameter(description = "ID of the flower to be retrieved") @PathVariable int id) {
        Optional<FlowerDTO> flower = Optional.ofNullable(flowerService.getFlowerById(id));

        return flower.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
