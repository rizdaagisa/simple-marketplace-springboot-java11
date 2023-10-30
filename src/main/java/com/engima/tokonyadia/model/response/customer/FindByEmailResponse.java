package com.engima.tokonyadia.model.response.customer;

import com.engima.tokonyadia.entity.Customer;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindByEmailResponse {

    private Customer customer;

}
