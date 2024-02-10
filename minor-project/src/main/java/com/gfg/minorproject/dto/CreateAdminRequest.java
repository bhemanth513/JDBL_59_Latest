package com.gfg.minorproject.dto;

import com.gfg.minorproject.model.Admin;
import com.gfg.minorproject.model.SecuredUser;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdminRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public Admin to(){
        return Admin.builder()
                .name(this.name)
                .securedUser(SecuredUser.builder()
                        .username(this.username)
                        .password(this.password)
                        .build())
                .build();
    }
}
