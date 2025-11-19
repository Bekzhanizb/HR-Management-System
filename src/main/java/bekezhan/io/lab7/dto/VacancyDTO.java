package bekezhan.io.lab7.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDTO {
    private Long id;
    private String title;
    private int salary;
    private boolean open;
}
