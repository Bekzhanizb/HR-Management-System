package bekezhan.io.lab7.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private int age;
    private String role;
    private String password;

    @ManyToOne
    private Vacancy vacancy;
}
