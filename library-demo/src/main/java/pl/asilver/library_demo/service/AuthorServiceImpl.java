package pl.asilver.library_demo.service;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import pl.asilver.library_demo.dto.AuthorCreateDTO;
import pl.asilver.library_demo.dto.AuthorDTO;
import pl.asilver.library_demo.dto.AuthorUpdateDTO;
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

    @Override
    public AuthorDTO getAuthorByNameV1(String name){
        Author author = authorRepository.findAuthorByName(name).orElseThrow();
        return convertToDto(author);
    }

    @Override
    public AuthorDTO getAuthorByNameV2(String name) {
        Author author = authorRepository.findAuthorByNameSQL(name).orElseThrow();
        return  convertToDto(author);
    }

    @Override
    public AuthorDTO getAuthorByNameV3(String name) {
        Specification<Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        });

        Author author = authorRepository.findOne(specification).orElseThrow();
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

    @Override
    public AuthorDTO createAuthor(AuthorCreateDTO authorCreateDTO){
        Author author = authorRepository.save(convertDTOtoEntity(authorCreateDTO));
        AuthorDTO authorDTO = convertEntityToDTO(author);
        return authorDTO;
    }

    @Override
    public AuthorDTO updateAuthor(AuthorUpdateDTO authorUpdateDTO){
        Author author = authorRepository.findById(authorUpdateDTO.getId()).orElseThrow();
        author.setName(authorUpdateDTO.getName());
        author.setSurname(authorUpdateDTO.getSurname());
        Author savedAuthor = authorRepository.save(author);
        AuthorDTO authorDto = convertEntityToDTO(savedAuthor);
        return authorDto;
    }

    @Override
    public void deleteAuthor(Long id){
        authorRepository.deleteById(id);
    }

    private Author convertDTOtoEntity(AuthorCreateDTO authorCreateDTO) {
        return Author.builder()
                .name(authorCreateDTO.getName())
                .surname(authorCreateDTO.getSurname())
                .build();
    }

    private AuthorDTO convertEntityToDTO(Author author) {
        List<BookDTO> bookDtoList = null;
        if (author.getBooks() != null) {
            bookDtoList = author.getBooks()
                    .stream()
                    .map(book -> BookDTO.builder()
                            .genre(book.getGenre().getName())
                            .name(book.getName())
                            .id(book.getId())
                            .build())
                    .toList();
        }

        AuthorDTO authorDto = AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .books(bookDtoList)
                .build();
        return authorDto;
    }
}
