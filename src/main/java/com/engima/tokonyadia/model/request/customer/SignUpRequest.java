package com.engima.tokonyadia.model.request.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String mobilePhone;
    private Boolean isMember;
    private String email;
    private String password;
}
