package com.gfg.minorproject.dto;


import com.gfg.minorproject.model.SecuredUser;
import com.gfg.minorproject.model.Student;
import lombok.*;

import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public Student to(){
        return Student.builder()
                .name(this.name)
                .contact(this.contact)
                .securedUser(SecuredUser.builder()
                        .username(username)
                        .password(password)
                        .build())
                .validity(new Date(System.currentTimeMillis()+60*60*24*365*1000))
                .build();
    }

}
