package com.example.catalogservice.jpa;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="catalog")
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String productId;
    @Column(nullable = false)
    private Integer qty;
    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault( value = "CURRENT_TIMESTAMP")
    private Date createdAt;
}
