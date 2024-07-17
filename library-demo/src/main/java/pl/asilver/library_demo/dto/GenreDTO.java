package pl.asilver.library_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.asilver.library_demo.dto.BookWithAuthorDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GenreDTO {
    private Long id;
    private String name;
    private List<BookWithAuthorDTO> authors;
}
