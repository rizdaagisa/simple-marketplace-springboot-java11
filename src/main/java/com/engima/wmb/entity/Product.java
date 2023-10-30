package com.engima.wmb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "m_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GenericGenerator(name="uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long price;

    @Column
    private Integer stock;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id")
    @JsonBackReference
    private Store store;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<TransactionDetail> transactionDetails;

}
