package Service;

import domain.dto.Mark;
import domain.dto.MarkDto;
import Exception.MarkNotFoundException;
import Exception.MarkAlreadyExistException;
import lombok.RequiredArgsConstructor;
import mapper.MarkMapper;
import repository.MarkRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarkDbService {
    private final MarkRepository markRepository;
    private final MarkMapper markMapper;

    public List<MarkDto> getAllMark() {
        List<Mark> mark = markRepository.findAll();
        return markMapper.mapToMarkDtoList(mark);
    }

    public MarkDto getMark(final long markId) throws MarkNotFoundException {
        Mark mark = markRepository.findById(markId).orElseThrow(() -> new MarkNotFoundException(markId));
        return markMapper.mapToMarkDto(mark);
    }

    @Transactional
    public MarkDto createMark(final MarkDto markDto) throws MarkAlreadyExistException {

        List<String> allMarksNames = getAllMark().stream()
                .map(MarkDto::getName)
                .collect(Collectors.toList());

        if (!allMarksNames.contains(markDto.getName())) {
            Mark mark = markMapper.mapToMark(markDto);
            Mark savedMark = markRepository.save(mark);
            return markMapper.mapToMarkDto(savedMark);
        } else {
            throw new MarkAlreadyExistException();
        }
    }

    @Transactional
    public MarkDto updateMark(final MarkDto markDto) throws MarkAlreadyExistException, MarkNotFoundException{

        List<String> allMarksNames = getAllMark().stream()
                .map(MarkDto::getName)
                .collect(Collectors.toList());

        if (!markRepository.existsById(markDto.getMarkId())) {
            throw new MarkNotFoundException(markDto.getMarkId());
        } else if (allMarksNames.contains(markDto.getName())) {
            throw new MarkAlreadyExistException();
        } else {
            Mark mark = markMapper.mapToMark(markDto);
            Mark savedMark = markRepository.save(mark);
            return markMapper.mapToMarkDto(savedMark);
        }
    }

    @Transactional
    public void deleteMark(final long markId) throws MarkNotFoundException {

        Optional<Mark> mark = markRepository.findById(markId);

        if (mark.isPresent()) {
            markRepository.deleteById(markId);
        } else {
            throw new MarkNotFoundException(markId);
        }
    }
}