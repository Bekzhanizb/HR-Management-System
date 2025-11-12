package bekezhan.io.lab7.dto;

import lombok.Data;

import java.util.Set;

@Data
public class VacancyDTO {
    private Long id;
    private String title;
    private int salary;
    private boolean open;
    private Set<Long> applicationsId;
}
