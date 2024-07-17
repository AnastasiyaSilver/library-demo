package pl.asilver.library_demo.repository;

import pl.asilver.library_demo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
