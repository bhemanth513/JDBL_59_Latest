package com.gfg.minorproject.dto;


import com.gfg.minorproject.model.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String contact;

    public Student to(){
        return Student.builder()
                .name(this.name)
                .contact(this.contact)
                .validity(new Date(System.currentTimeMillis()+60*60*24*365*1000))
                .build();
    }

}
