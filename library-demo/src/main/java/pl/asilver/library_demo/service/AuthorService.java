package pl.asilver.library_demo.service;

import pl.asilver.library_demo.dto.AuthorCreateDTO;
import pl.asilver.library_demo.dto.AuthorDTO;
import pl.asilver.library_demo.dto.AuthorUpdateDTO;
import pl.asilver.library_demo.model.Author;

public interface AuthorService {
    AuthorDTO getAuthorById(Long id);

    AuthorDTO getAuthorByNameV1(String name);

    AuthorDTO getAuthorByNameV2(String name);

    AuthorDTO getAuthorByNameV3(String name);

    AuthorDTO createAuthor(AuthorCreateDTO authorCreateDTO);

    AuthorDTO updateAuthor(AuthorUpdateDTO authorUpdateDTO);

    void deleteAuthor(Long id);
}
