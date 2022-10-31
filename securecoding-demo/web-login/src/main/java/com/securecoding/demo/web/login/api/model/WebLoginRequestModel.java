package com.securecoding.demo.web.login.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebLoginRequestModel {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
