package pl.asilver.library_demo.service;

import pl.asilver.library_demo.dto.BookDTO;

public interface BookService {
    BookDTO getByNameV1(String name);

    BookDTO getByNameV2(String name);

    BookDTO getByNameV3(String name);
}
