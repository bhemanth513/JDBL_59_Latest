package com.gfg.minorproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String contact;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties({"student"})
    private List<Book> studentBookList;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties({"student"})
    private List<Transaction> transactionList;

    private Date validity;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties(value = {"admin"})
    SecuredUser securedUser;

}
