package pl.asilver.library_demo.controller.rest;

import pl.asilver.library_demo.dto.GenreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.asilver.library_demo.service.GenreService;

@RestController
@RequiredArgsConstructor
public class GenreRestController {

    private final GenreService genreService;

    @GetMapping("/genre/{id}")
    GenreDTO getGenreById(@PathVariable("id") Long id) {
        return genreService.getGenreById(id);
    }
}
