package pl.asilver.library_demo.service;

import pl.asilver.library_demo.dto.AuthorDTO;
import pl.asilver.library_demo.dto.BookWithAuthorDTO;
import pl.asilver.library_demo.dto.GenreDTO;
import lombok.RequiredArgsConstructor;
import pl.asilver.library_demo.model.Genre;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.asilver.library_demo.repository.GenreRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDto(genre);
    }


    private GenreDTO convertToDto(Genre genre) {
        List<BookWithAuthorDTO> bookDtoList = genre.getBooks()
                .stream()
                .map(book -> BookWithAuthorDTO.builder()
                        .name(book.getName())
                        .id(book.getId())
                        .authors(book.getAuthors().stream()
                                .map(author -> AuthorDTO.builder()
                                        .id(author.getId())
                                        .name(author.getName())
                                        .surname(author.getSurname())
                                        .build()
                                ).toList()
                        )
                        .build()
                ).toList();
        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
