package pl.asilver.library_demo.service;

import pl.asilver.library_demo.dto.AuthorDTO;
import pl.asilver.library_demo.dto.BookDTO;
import pl.asilver.library_demo.dto.GenreDTO;
import lombok.RequiredArgsConstructor;
import pl.asilver.library_demo.model.Genre;
import org.springframework.stereotype.Service;
import pl.asilver.library_demo.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDto(genre);
    }


    private GenreDTO convertToDto (Genre genre) {
        List <BookDTO> bookDtoList = genre.getBooks()
                .stream()
                .map(book -> BookDTO.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .authors(book.getAuthors().stream()
                                .map(author -> AuthorDTO.builder()
                                        .id(author.getId())
                                        .name(author.getName())
                                        .surname(author.getSurname())
                                        .build()).toList())
                        .build()).toList();
        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .books(bookDtoList)
                .build();
    }
}
