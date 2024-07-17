package service;

import dto.AuthorDTO;
import dto.BookWithAuthorDTO;
import dto.GenreDTO;
import lombok.RequiredArgsConstructor;
import model.Genre;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.GenreRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(GenreServiceImpl.class);

    @Override
    public GenreDTO getGenreById(Long id) {
        logger.info("Fetching genre with id: {}");
        Genre genre = genreRepository.findById(id).orElseThrow(() -> {
            logger.info("Genre not found with id: {}");
            return new RuntimeException("Genre not found");
        });
        logger.info("Found genre: {}");
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
