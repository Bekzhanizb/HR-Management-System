package bekezhan.io.lab7.mapper;

import bekezhan.io.lab7.dto.VacancyDTO;
import bekezhan.io.lab7.entity.User;
import bekezhan.io.lab7.entity.Vacancy;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class VacancyMapper {
    public VacancyDTO toVacancyDTO(Vacancy vacancy) {
        VacancyDTO vacancyDTO = new VacancyDTO();
        vacancyDTO.setId(vacancy.getId());
        vacancyDTO.setTitle(vacancy.getTitle());
        vacancyDTO.setSalary(vacancy.getSalary());
        vacancyDTO.setOpen(vacancy.isOpen());
        vacancyDTO.setApplicationsId(vacancy.getApplications().stream().map(User::getId).collect(Collectors.toSet()));
        return vacancyDTO;
    }
}
