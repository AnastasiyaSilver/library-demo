package pl.asilver.library_demo.service;

import pl.asilver.library_demo.dto.AuthorDTO;

public interface AuthorService {
    AuthorDTO getAuthorById(Long id);

    AuthorDTO getAuthorByNameV1(String name);

    AuthorDTO getAuthorByNameV2(String name);

    AuthorDTO getAuthorByNameV3(String name);
}
