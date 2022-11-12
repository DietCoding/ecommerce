package com.example.userservice.jpa;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50, unique = false)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = false)
    private String userId;
    @Column(nullable = false, unique = false)
    private String encryptedPwd;
}
