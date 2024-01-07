package com.gfg.minorproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

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
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private Integer pages;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"booklist"})
    private Author my_author;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"studentBookList"})
    private Student student;

    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER )
    @JsonIgnoreProperties({"book"})
    private List<Transaction> transactionList;

    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

}
