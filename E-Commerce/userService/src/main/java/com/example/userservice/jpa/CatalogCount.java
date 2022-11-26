package com.example.userservice.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CatalogCount {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = false)
    private int catalogId;
    @Column(nullable = false, unique = false)
    private int count;
}
