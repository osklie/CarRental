package controller;

import Service.MarkDbService;
import domain.dto.MarkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/mark")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MarkController {

    private final MarkDbService markDbService;

    @GetMapping
    public List<MarkDto> getMark() {
        return markDbService.getAllMark();
    }

    @GetMapping(value = "{markId}")
    public MarkDto getMark(@PathVariable long mark) {
        return markDbService.getMark (mark);
    }

    @DeleteMapping(value = "{markId}")
    public void deleteMark(@PathVariable long mark) {
        markDbService.deleteMark(mark);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public MarkDto addMark(@RequestBody MarkDto markDto){
        return markDbService.createMark(markDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public MarkDto updateMark(@RequestBody MarkDto markDto) {
        return markDbService.updateMark(markDto);
    }
}
