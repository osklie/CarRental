package mapper;

import domain.dto.Mark;
import domain.dto.MarkDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarkMapper {

    public Mark mapToMark(final MarkDto markDto) {
        return new Mark(
                markDto.getMarkId(),
                markDto.getName(),
                markDto.getModels()
        );
    }

    public MarkDto mapToMarkDto(final Mark mark) {
        return new MarkDto(
                mark.getMarkId(),
                mark.getName(),
                mark.getModels()
        );
    }

    public List<MarkDto> mapToMarkDtoList(final List<Mark> markList) {
        return markList.stream()
                .map(this::mapToMarkDto)
                .collect(Collectors.toList());
    }
}