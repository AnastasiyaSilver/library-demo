package pl.asilver.library_demo.repository;

import pl.asilver.library_demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
