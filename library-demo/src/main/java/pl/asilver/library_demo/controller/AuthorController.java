package pl.asilver.library_demo.controller;

import org.springframework.web.bind.annotation.*;
import pl.asilver.library_demo.dto.AuthorCreateDTO;
import pl.asilver.library_demo.dto.AuthorDTO;
import lombok.RequiredArgsConstructor;
import pl.asilver.library_demo.dto.AuthorUpdateDTO;
import pl.asilver.library_demo.service.AuthorService;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/author/{id}")
    AuthorDTO getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/author")
    AuthorDTO getAuthorByNameV1(@RequestParam("name") String name) {
        return authorService.getAuthorByNameV1(name);
    }

    @GetMapping("/author/v2")
    AuthorDTO getAuthorByNameV2(@RequestParam("name") String name){
        return authorService.getAuthorByNameV2(name);
    }

    @GetMapping("/author/v3")
    AuthorDTO getAuthorByNameV3(@RequestParam("name") String name){
        return authorService.getAuthorByNameV3(name);
    }

    @PostMapping("/author/create")
    AuthorDTO createAuthor(@RequestBody AuthorCreateDTO authorCreateDTO){
        return authorService.createAuthor(authorCreateDTO);
    }

    @PutMapping("/author/update")
    AuthorDTO updateAuthor(@RequestBody AuthorUpdateDTO authorUpdateDTO){
        return authorService.updateAuthor(authorUpdateDTO);
    }

    @DeleteMapping("author/delete/{id}")
    void deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthor(id);
    }
}
