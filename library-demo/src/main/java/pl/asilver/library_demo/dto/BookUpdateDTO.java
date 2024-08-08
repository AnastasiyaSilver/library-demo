package pl.asilver.library_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookUpdateDTO {
    private Long id;
    private String name;
    private Long genreId;
    private List<Long> authorIds;
}
