package com.gfg.minorproject.dto;

import com.gfg.minorproject.model.Student;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String contact;

    private Date validity;

    public Student to(){
        return Student.builder()
                .name(this.name)
                .contact(this.contact)
                .validity(this.validity)
                .build();
    }
}
