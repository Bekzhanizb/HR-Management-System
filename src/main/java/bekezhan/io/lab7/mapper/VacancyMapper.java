package bekezhan.io.lab7.mapper;

import bekezhan.io.lab7.dto.VacancyDTO;
import bekezhan.io.lab7.entity.Vacancy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VacancyMapper {
    VacancyDTO toDTO(Vacancy vacancy);
    Vacancy toEntity(VacancyDTO dto);
}
