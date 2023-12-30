package com.example.demojpa.dto;

import com.example.demojpa.models.Book;
import com.example.demojpa.models.BookCategory;
import com.example.demojpa.models.Language;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {
    @NotBlank
    private String name;
    private String authorName;
    private String publisherName;
    private int pages;
    private BookCategory bookCategory;
    private Language language;

    public Book to(){
       return Book.builder()
               .name(this.name)
               .authorName(this.authorName)
               .publisherName(this.publisherName)
               .bookCategory(this.bookCategory)
               .pages(this.pages)
               .language(this.language)
               .build();
    }
}
