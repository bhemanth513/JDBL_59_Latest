package com.gfg.minorproject.dto;
import com.gfg.minorproject.model.Author;
import com.gfg.minorproject.model.Book;
import com.gfg.minorproject.model.Genre;
import com.gfg.minorproject.model.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @NotBlank
    private String name;
    @NotNull
    private Genre genre;
    private Integer pages;
    @NotBlank
    private String authorName;
    private String authorCountry;
    @NotBlank
    private String authorEmail;

    public Book to() {
        return Book.builder()
                .name(this.name)
                .genre(this.genre)
                .pages(this.pages)
                .my_author(Author.builder()
                        .name(this.authorName)
                        .country(this.authorCountry)
                        .email(this.authorEmail)
                        .build())
                .build();
    }
}
