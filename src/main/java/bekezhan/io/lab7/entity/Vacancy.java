package bekezhan.io.lab7.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int salary;
    private boolean open = true;

    @ManyToMany
    @JoinTable(
            name = "applications",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> applications = new HashSet<>();
}
