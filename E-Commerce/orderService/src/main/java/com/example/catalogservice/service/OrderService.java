package com.example.catalogservice.service;

import com.example.catalogservice.dto.OrderDto;
import com.example.catalogservice.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId); //반복적인(Iterable) 데이터에서. DB에서 바로 가져올꺼기 때문에.  UserEntity 가공할꺼면 UserDto로 쓸수 있따.

    };

