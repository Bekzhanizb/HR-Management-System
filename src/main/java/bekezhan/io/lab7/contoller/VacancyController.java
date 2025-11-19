package bekezhan.io.lab7.contoller;

import bekezhan.io.lab7.dto.VacancyDTO;
import bekezhan.io.lab7.mapper.VacancyMapper;
import bekezhan.io.lab7.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;
    private final VacancyMapper vacancyMapper;

    @GetMapping
    public List<VacancyDTO> getAll() {
        return vacancyService.findAll()
                .stream()
                .map(vacancyMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public VacancyDTO getById(@PathVariable Long id) {
        return vacancyMapper.toDTO(vacancyService.findById(id));
    }

    @PostMapping
    public VacancyDTO create(
            @RequestParam String title,
            @RequestParam int salary,
            @RequestParam(defaultValue = "true") boolean open
    ) {
        VacancyDTO dto = new VacancyDTO();
        dto.setTitle(title);
        dto.setSalary(salary);
        dto.setOpen(open);

        return vacancyMapper.toDTO(
                vacancyService.create(vacancyMapper.toEntity(dto))
        );
    }

    @PutMapping("/{id}")
    public VacancyDTO update(
            @PathVariable Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer salary,
            @RequestParam(required = false) Boolean open
    ) {
        VacancyDTO dto = new VacancyDTO();
        if (title != null) dto.setTitle(title);
        if (salary != null) dto.setSalary(salary);
        if (open != null) dto.setOpen(open);

        return vacancyMapper.toDTO(vacancyService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vacancyService.delete(id);
    }
}
