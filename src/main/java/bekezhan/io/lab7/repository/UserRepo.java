package bekezhan.io.lab7.repository;

import bekezhan.io.lab7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
