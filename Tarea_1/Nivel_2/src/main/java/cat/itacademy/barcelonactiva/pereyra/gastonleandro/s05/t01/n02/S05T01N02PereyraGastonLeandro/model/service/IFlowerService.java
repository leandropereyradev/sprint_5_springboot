package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.model.service;

import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.model.dto.FlowerDTO;

import java.util.List;

public interface IFlowerService {

    List<FlowerDTO> addFlowers(List<FlowerDTO> flowerDTOs);

    FlowerDTO updateFlower(Integer id, FlowerDTO flowerDTO);

    boolean deleteFlower(Integer id);

    FlowerDTO getFlowerById(Integer id);

    List<FlowerDTO> getAllFlowers();
}
