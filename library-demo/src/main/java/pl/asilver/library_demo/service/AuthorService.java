package pl.asilver.library_demo.service;

import pl.asilver.library_demo.dto.AuthorDTO;

public interface AuthorService {
    AuthorDTO getAuthorById(Long id);
}
