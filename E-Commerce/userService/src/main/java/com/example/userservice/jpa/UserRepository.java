package com.example.userservice.jpa;

import lombok.Data;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUserId(String userId);

}
