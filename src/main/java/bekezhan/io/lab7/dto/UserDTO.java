package bekezhan.io.lab7.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private int age;
    private String role;
    private String password;
    private Long vacancyId;
}
