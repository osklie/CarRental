package Service;

import domain.dto.Model;
import domain.dto.ModelDto;
import lombok.RequiredArgsConstructor;
import mapper.ModelMapper;
import org.springframework.stereotype.Service;
import repository.ModelRepository;
import Exception.ModelAlreadyExistException;
import Exception.ModelNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelDbService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public List<ModelDto> getModels() {
        List<Model> models = modelRepository.findAll();
        return modelMapper.mapToModelDtoList(models);
    }

    public ModelDto getModel(final long modelId) throws ModelNotFoundException {
        Model model = modelRepository.findById(modelId).orElseThrow(() -> new ModelNotFoundException(modelId));
        return modelMapper.mapToModelDto(model);
    }

    @Transactional
    public ModelDto createModel(final Model modelDto) throws ModelAlreadyExistException {

        List<Integer> modelHashCodes = getModels().stream()
                .map(ModelDto::hashCode)
                .collect(Collectors.toList());

        if (!modelHashCodes.contains(modelDto.hashCode())) {
            Model model = modelMapper.mapToModel(modelDto);
            Model savedModel = modelRepository.save(model);
            return modelMapper.mapToModelDto(savedModel);
        } else {
            throw new ModelAlreadyExistException();
        }
    }

    @Transactional
    public ModelDto updateModel(final Model modelDto) throws ModelNotFoundException, ModelAlreadyExistException {

        List<Integer> modelHashCodes = getModels().stream()
                .map(ModelDto::hashCode)
                .collect(Collectors.toList());

        if (!modelRepository.existsById(modelDto.getModelId())) {
            throw new ModelNotFoundException(modelDto.getModelId());
        } else if (modelHashCodes.contains(modelDto.hashCode())) {
            throw new ModelAlreadyExistException();
        } else {
            Model model = modelMapper.mapToModel(modelDto);
            Model savedModel = modelRepository.save(model);
            return modelMapper.mapToModelDto(savedModel);
        }
    }

    @Transactional
    public void deleteModel(final long modelId) throws ModelNotFoundException {
        Optional<Model> model = modelRepository.findById(modelId);

        if (model.isPresent()) {
            modelRepository.deleteById(modelId);
        } else {
            throw new ModelNotFoundException(modelId);
        }
    }
}