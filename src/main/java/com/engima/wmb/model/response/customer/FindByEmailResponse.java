package com.engima.wmb.model.response.customer;

import com.engima.wmb.entity.Customer;
import com.engima.wmb.entity.UserCredential;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindByEmailResponse {

    private Customer customer;

}
