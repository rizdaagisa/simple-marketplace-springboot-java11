package com.engima.wmb.model.request.customer;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginRequest {
    private String email;
    private String password;

}
