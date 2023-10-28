package com.engima.wmb.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DataResponse <T>{

    public Integer status;
    public String message;
    private T data;

}
