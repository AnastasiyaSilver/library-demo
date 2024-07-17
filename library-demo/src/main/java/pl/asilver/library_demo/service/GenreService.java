package pl.asilver.library_demo.service;

import pl.asilver.library_demo.dto.GenreDTO;

public interface GenreService {
    GenreDTO getGenreById(Long id);
}
