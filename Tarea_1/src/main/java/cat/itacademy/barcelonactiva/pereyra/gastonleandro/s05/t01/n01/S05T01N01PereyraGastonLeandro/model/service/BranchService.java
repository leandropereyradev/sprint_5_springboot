package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.model.service;

import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.exception.BranchServiceException;
import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.model.domain.Branch;
import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.model.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<BranchDTO> addBranches(List<BranchDTO> branchDTOs) {
        try {
            List<Branch> branches = branchDTOs.stream().map(dto -> {
                Branch branch = new Branch();
                branch.setBranchName(dto.getBranchName());
                branch.setBranchCountry(dto.getBranchCountry());
                return branch;
            }).collect(Collectors.toList());

            List<Branch> savedBranches = branchRepository.saveAll(branches);

            return savedBranches.stream().map(branch -> new BranchDTO(
                    branch.getPk_BranchID(),
                    branch.getBranchName(),
                    branch.getBranchCountry()
            )).collect(Collectors.toList());
        } catch (Exception e) {
            throw new BranchServiceException("Failed to add branches", e);
        }
    }

    public BranchDTO updateBranch(Integer id, BranchDTO branchDTO) {
        try {
            Optional<Branch> existingBranch = branchRepository.findById(id);

            if (existingBranch.isEmpty()) {
                throw new BranchServiceException("Branch not found");
            }

            Branch branch = existingBranch.get();
            branch.setBranchName(branchDTO.getBranchName());
            branch.setBranchCountry(branchDTO.getBranchCountry());
            Branch updatedBranch = branchRepository.save(branch);

            return new BranchDTO(
                    updatedBranch.getPk_BranchID(),
                    updatedBranch.getBranchName(),
                    updatedBranch.getBranchCountry()
            );
        } catch (Exception e) {
            throw new BranchServiceException("Failed to update branch", e);
        }
    }

    public boolean deleteBranch(Integer id) {
        try {
            if (!branchRepository.existsById(id)) {
                throw new BranchServiceException("Branch not found");
            }

            branchRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new BranchServiceException("Failed to delete branch", e);
        }
    }

    public BranchDTO getBranchById(Integer id) {
        try {
            Optional<Branch> branch = branchRepository.findById(id);

            return branch.map(value -> new BranchDTO(
                    value.getPk_BranchID(),
                    value.getBranchName(),
                    value.getBranchCountry()
            )).orElse(null);
        } catch (Exception e) {
            throw new BranchServiceException("Failed to get branch by ID", e);
        }
    }

    public List<BranchDTO> getAllBranches() {
        try {
            return branchRepository.findAll().stream()
                    .map(branch -> new BranchDTO(
                            branch.getPk_BranchID(),
                            branch.getBranchName(),
                            branch.getBranchCountry()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BranchServiceException("Failed to get all branches", e);
        }
    }
}
