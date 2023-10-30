package com.engima.wmb.model.response.customer;

public class UserCredentialResponse {
    private String id;
    private String email;
    CustomerResponse customerResponse;

    public UserCredentialResponse(String id, String email, CustomerResponse customerResponse) {
        this.id = id;
        this.email = email;
        this.customerResponse = customerResponse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerResponse getCustomerResponse() {
        return customerResponse;
    }

    public void setCustomerResponse(CustomerResponse customerResponse) {
        this.customerResponse = customerResponse;
    }

    @Override
    public String toString() {
        return "UserCredentialResponse{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", customerResponse=" + customerResponse +
                '}';
    }
}
