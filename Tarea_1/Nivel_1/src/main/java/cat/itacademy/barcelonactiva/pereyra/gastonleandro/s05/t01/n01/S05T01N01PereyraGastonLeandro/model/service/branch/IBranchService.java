package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.model.service.branch;

import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n01.S05T01N01PereyraGastonLeandro.model.dto.BranchDTO;

import java.util.List;

public interface IBranchService {
    void addBranch(BranchDTO branchDTO);

    void updateBranch(BranchDTO branchDTO);

    void deleteBranch(Integer id);

    BranchDTO getBranchById(Integer id);

    List<BranchDTO> getAllBranches();
}
