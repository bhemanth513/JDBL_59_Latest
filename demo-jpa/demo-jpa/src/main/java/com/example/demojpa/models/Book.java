package com.example.demojpa.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String authorName;
    private String publisherName;
    @Column(name = "book_pages")
    private int pages;
    @Enumerated(value = EnumType.STRING)
    private BookCategory bookCategory;
    @Enumerated()
    private Language language;

    @CreationTimestamp
    private Date createdOn; //Insert into
    @UpdateTimestamp
    private Date updatedOn; //taken care when you perform insert into, update, delete
}
