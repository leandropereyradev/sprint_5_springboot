package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.controller;

import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.exception.BranchServiceException;
import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.model.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/branch")
@Slf4j
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("/add")
    public ResponseEntity<List<BranchDTO>> addBranches(@RequestBody List<BranchDTO> branchDTOs) {
        try {
            List<BranchDTO> addedBranches = branchService.addBranches(branchDTOs);

            return ResponseEntity.ok(addedBranches);

        } catch (BranchServiceException e) {
            System.err.println("Error adding branches: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable int id, @RequestBody BranchDTO branchDTO) {
        try {
            BranchDTO updatedBranch = branchService.updateBranch(id, branchDTO);

            if (updatedBranch == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

            return ResponseEntity.ok(updatedBranch);

        } catch (BranchServiceException e) {
            System.err.println("Error updating branch: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable int id) {
        try {
            boolean isDeleted = branchService.deleteBranch(id);

            if (!isDeleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            return ResponseEntity.noContent().build();

        } catch (BranchServiceException e) {
            System.err.println("Error deleting branch: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        try {
            List<BranchDTO> branches = branchService.getAllBranches();

            return ResponseEntity.ok(branches);

        } catch (BranchServiceException e) {
            System.err.println("Error getting all branches: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable int id) {
        try {
            Optional<BranchDTO> branch = Optional.ofNullable(branchService.getBranchById(id));

            return branch.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

        } catch (BranchServiceException e) {
            System.err.println("Error getting branch by ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
