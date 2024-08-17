package pl.asilver.library_demo.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.asilver.library_demo.dto.AuthorDTO;
import pl.asilver.library_demo.dto.BookCreateDTO;
import pl.asilver.library_demo.dto.BookDTO;
import pl.asilver.library_demo.dto.BookUpdateDTO;
import pl.asilver.library_demo.model.Author;
import pl.asilver.library_demo.model.Book;
import pl.asilver.library_demo.model.Genre;
import pl.asilver.library_demo.repository.AuthorRepository;
import pl.asilver.library_demo.repository.BookRepository;
import pl.asilver.library_demo.repository.GenreRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public BookDTO getByNameV1(String name) {
        Book book = bookRepository.findBookByName(name).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDTO getByNameV2(String name) {
        Book book = bookRepository.findBookByNameBySql(name).orElseThrow();
        return convertEntityToDto(book);
    }

    private BookDTO convertEntityToDto(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .genre(book.getGenre().getName())
                .name(book.getName())
                .build();
    }

    @Override
    public BookDTO getByNameV3(String name) {
        Specification<Book> specification = Specification.where(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        });

        Book book = bookRepository.findOne(specification).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDTO createBook(BookCreateDTO bookCreateDTO) {
        Book book = convertDTOtoEntity(bookCreateDTO);
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    @Override
    public BookDTO updateBook(BookUpdateDTO bookUpdateDTO) {
        Book book = bookRepository.findById(bookUpdateDTO.getId()).orElseThrow();

        book.setName(bookUpdateDTO.getName());
        book.setGenre(genreRepository.findById(bookUpdateDTO.getGenreId()).orElseThrow());
        Set<Author> authors = new HashSet<>(authorRepository.findAllById(bookUpdateDTO.getAuthorIds()));
        book.setAuthors(authors);

        Book updatedBook = bookRepository.save(book);
        return convertToDto(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    private BookDTO convertToDto(Book book) {
        List<AuthorDTO> authorDtos = book.getAuthors()
                .stream()
                .map(author -> AuthorDTO.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .surname(author.getSurname())
                        .build()
                ).toList();

        return BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .authors(authorDtos)
                .build();
    }

    private Book convertDTOtoEntity(BookCreateDTO bookCreateDTO) {
        Genre genre = genreRepository.findById(bookCreateDTO.getGenreId()).orElseThrow();
        Set<Author> authors = new HashSet<>(authorRepository.findAllById(bookCreateDTO.getAuthorIds()));

        return Book.builder()
                .name(bookCreateDTO.getName())
                .genre(genre)
                .authors(authors)
                .build();
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
}
