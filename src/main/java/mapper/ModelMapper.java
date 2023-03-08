package mapper;

import domain.dto.Model;
import domain.dto.ModelDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelMapper {

    public Model mapToModel(final Model modelDto) {
        return new Model(
                modelDto.getModelId(),
                modelDto.getMark(),
                modelDto.getName(),
                modelDto.getEngineSize(),
                modelDto.getCarType(),
                modelDto.getProductionYear(),
                modelDto.getColor(),
                modelDto.getSeatsQuantity(),
                modelDto.getDoorQuantity(),
                modelDto.getFuelType(),
                modelDto.getGearBox(),
                modelDto.getCars()
        );
    }

    public ModelDto mapToModelDto(final Model model) {
        return new ModelDto(
                model.getModelId(),
                model.getMark(),
                model.getName(),
                model.getEngineSize(),
                model.getCarType(),
                model.getProductionYear(),
                model.getColor(),
                model.getSeatsQuantity(),
                model.getDoorQuantity(),
                model.getFuelType(),
                model.getGearBox(),
                model.getCars()
        );
    }

    public List<ModelDto> mapToModelDtoList(final List<Model> modelList) {
        return modelList.stream()
                .map(this::mapToModelDto)
                .collect(Collectors.toList());
    }
}