package pl.asilver.library_demo.service;

import pl.asilver.library_demo.dto.AuthorCreateDTO;
import pl.asilver.library_demo.dto.BookCreateDTO;
import pl.asilver.library_demo.dto.BookDTO;
import pl.asilver.library_demo.dto.BookUpdateDTO;

public interface BookService {
    BookDTO getByNameV1(String name);

    BookDTO getByNameV2(String name);

    BookDTO getByNameV3(String name);

    BookDTO createBook(BookCreateDTO bookCreateDTO);

    BookDTO updateBook(BookUpdateDTO bookUpdateDTO);
    void deleteBook(Long id);
}
