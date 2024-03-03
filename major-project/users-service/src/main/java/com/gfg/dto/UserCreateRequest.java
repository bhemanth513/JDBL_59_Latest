package com.gfg.dto;

import com.gfg.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UserCreateRequest {
    private String name;
    @NotBlank
    private String mobile;
    private String email;
    public User to(){
        return User.builder()
                .name(this.name)
                .mobile(this.mobile)
                .email(this.email)
                .build();
    }
}
