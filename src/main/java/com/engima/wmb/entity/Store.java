package com.engima.wmb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "m_store")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone_number;

    @Column
    private String siup_number;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;

}
