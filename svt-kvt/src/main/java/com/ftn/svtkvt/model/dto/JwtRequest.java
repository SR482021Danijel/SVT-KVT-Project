package com.ftn.svtkvt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
