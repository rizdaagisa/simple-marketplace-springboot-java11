package com.engima.tokonyadia.model.response.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResponse {
    private Integer id;
    private String customer_name;
    private String phone_number;
    private String user_credential_id;

}
