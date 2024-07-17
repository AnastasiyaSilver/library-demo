package pl.asilver.library_demo.service;
import pl.asilver.library_demo.dto.AuthorDTO;
import pl.asilver.library_demo.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import pl.asilver.library_demo.model.Author;
import org.springframework.stereotype.Service;
import pl.asilver.library_demo.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return convertToDto(author);
    }

    private AuthorDTO convertToDto(Author author) {
        List<BookDTO> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDTO.builder()
                        .genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .build()
                ).toList();
        return AuthorDTO.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }
}
