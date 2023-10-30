package com.engima.tokonyadia.model.request.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreSignUpRequest {

    private String name;
    private String address;
    private String phone_number;
    private String siup_number;

}
