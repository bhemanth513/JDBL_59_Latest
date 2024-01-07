package com.gfg.minorproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String country;

    @Column(unique = true,nullable = false)
    private String email;

    @CreationTimestamp
    private Date addedOn;

    @JsonIgnore
    @OneToMany(mappedBy = "my_author",cascade=CascadeType.ALL)
    @JsonIgnoreProperties({"my_author"})
    private List<Book> bookList;
}
