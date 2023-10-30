package com.engima.wmb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "m_customer")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GenericGenerator(name="uuid", strategy ="uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String mobilePhone;

    @Column(name = "is_member")
    private Boolean isMember = false;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_credential_id" , foreignKey= @ForeignKey(name = "Fk_user_credential"))
    @JsonManagedReference
    private UserCredential userCredential;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transaction> transactions;

}
