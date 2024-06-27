package cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.model.service;

import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.exception.FlowerServiceException;
import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.model.domain.FlowerEntity;
import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.pereyra.gastonleandro.s05.t01.n02.S05T01N02PereyraGastonLeandro.model.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImpl implements IFlowerService {

    @Autowired
    private FlowerRepository flowerRepository;

    @Override
    public List<FlowerDTO> addFlowers(List<FlowerDTO> flowerDTOs) {
        try {
            List<FlowerEntity> flowers = flowerDTOs.stream().map(dto -> {
                FlowerEntity flower = new FlowerEntity();
                flower.setFlowerName(dto.getFlowerName());
                flower.setFlowerCountry(dto.getFlowerCountry());

                return flower;

            }).collect(Collectors.toList());

            List<FlowerEntity> savedFlowers = flowerRepository.saveAll(flowers);

            return savedFlowers.stream().map(flower -> new FlowerDTO(
                    flower.getPk_FlowerID(),
                    flower.getFlowerName(),
                    flower.getFlowerCountry()

            )).collect(Collectors.toList());

        } catch (Exception e) {
            throw new FlowerServiceException("Failed to add flowers", e);
        }
    }

    @Override
    public FlowerDTO updateFlower(Integer id, FlowerDTO flowerDTO) {
        try {
            Optional<FlowerEntity> existingFlower = flowerRepository.findById(id);

            if (existingFlower.isEmpty()) throw new FlowerServiceException("Flower not found");

            FlowerEntity flower = existingFlower.get();
            flower.setFlowerName(flowerDTO.getFlowerName());
            flower.setFlowerCountry(flowerDTO.getFlowerCountry());
            FlowerEntity updatedFlower = flowerRepository.save(flower);

            return new FlowerDTO(
                    updatedFlower.getPk_FlowerID(),
                    updatedFlower.getFlowerName(),
                    updatedFlower.getFlowerCountry()
            );

        } catch (Exception e) {
            throw new FlowerServiceException("Failed to update flower", e);
        }
    }

    @Override
    public boolean deleteFlower(Integer id) {
        try {
            if (!flowerRepository.existsById(id)) throw new FlowerServiceException("Flower not found");

            flowerRepository.deleteById(id);
            return true;

        } catch (Exception e) {
            throw new FlowerServiceException("Failed to delete flower", e);
        }
    }

    @Override
    public FlowerDTO getFlowerById(Integer id) {
        try {
            Optional<FlowerEntity> flower = flowerRepository.findById(id);

            return flower.map(value -> new FlowerDTO(
                    value.getPk_FlowerID(),
                    value.getFlowerName(),
                    value.getFlowerCountry()

            )).orElse(null);

        } catch (Exception e) {
            throw new FlowerServiceException("Failed to get flower by ID", e);
        }
    }

    @Override
    public List<FlowerDTO> getAllFlowers() {
        try {
            return flowerRepository.findAll().stream()
                    .map(flower -> new FlowerDTO(
                            flower.getPk_FlowerID(),
                            flower.getFlowerName(),
                            flower.getFlowerCountry()

                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new FlowerServiceException("Failed to get all flowers", e);
        }
    }
}
