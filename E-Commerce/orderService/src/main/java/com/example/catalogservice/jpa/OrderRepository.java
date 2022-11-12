package com.example.catalogservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    OrderEntity findByOrderId(String productId);
    Iterable<OrderEntity> findByUserId(String userId);

}
