package pl.asilver.library_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.asilver.library_demo.dto.AuthorDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookWithAuthorDTO {
    private Long id;
    private String name;
    private List<AuthorDTO> authors;
}
