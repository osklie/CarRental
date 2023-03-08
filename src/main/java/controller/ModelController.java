package controller;

import Service.ModelDbService;
import domain.dto.Model;
import domain.dto.ModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import Exception.ModelAlreadyExistException;
import Exception.ModelNotFoundException;

import java.util.List;

@RestController
@RequestMapping("v1/models")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ModelController {

    private final ModelDbService modelDbService;

    @GetMapping
    public List<ModelDto> getModels() {
        return modelDbService.getModels();
    }

    @GetMapping(value = "{modelId}")
    public ModelDto getModel(@PathVariable long modelId) {
        return modelDbService.getModel(modelId);
    }

    @DeleteMapping(value = "{modelId}")
    public void deleteModel(@PathVariable long modelId) {
        modelDbService.deleteModel(modelId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelDto addModel(@RequestBody Model model) {
        try {
            return modelDbService.createModel(model);
        } catch (ModelAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelDto updateModel(@RequestBody Model model) {
        try {
            return modelDbService.updateModel(model);
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ModelAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }
}