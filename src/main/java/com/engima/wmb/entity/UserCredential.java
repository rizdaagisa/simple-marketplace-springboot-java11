package com.engima.wmb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_user_credential")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredential {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    private String email;

    private String password;

    @OneToOne(mappedBy = "userCredential", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Customer customer;

}
