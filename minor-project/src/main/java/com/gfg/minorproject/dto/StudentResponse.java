package com.gfg.minorproject.dto;

import com.gfg.minorproject.model.Student;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse implements Serializable {

    private int id;

    private String name;

    private String contact;
    private Date createdOn;
    private Date updatedOn;

    private Date validity;

     public StudentResponse(Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.contact = student.getContact();
        this.createdOn = student.getCreatedOn();
        this.updatedOn = student.getUpdatedOn();
    }
}
