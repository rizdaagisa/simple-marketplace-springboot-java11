package com.engima.tokonyadia.model.response.customer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResponse {

    private String name;
    private String mobilePhone;
    private Boolean isMember = false;
    private String email;
    private String password;

}
