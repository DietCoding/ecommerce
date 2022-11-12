package com.example.catalogservice.controller;

import com.example.catalogservice.dto.OrderDto;
import com.example.catalogservice.jpa.OrderEntity;
import com.example.catalogservice.service.OrderService;
import com.example.catalogservice.vo.RequestOrder;
import com.example.catalogservice.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class OrderController
{
    private Environment env;
    OrderService orderService;

    @Autowired
    public OrderController(Environment env, OrderService orderService){
        this.env = env;
        this.orderService = orderService;
    }

    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {
        return String.format("It's Working in Order Service on Port %s", request.getServerPort());
    }

    @PostMapping(value="/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                                     @RequestBody RequestOrder orderDetails) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);
        OrderDto createDto = orderService.createOrder(orderDto);
        ResponseOrder returnValue = mapper.map(createDto, ResponseOrder.class);

        // ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping(value="/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId){
        Iterable<OrderEntity> userList = orderService.getOrdersByUserId(userId);
        List<ResponseOrder> result = new ArrayList<>();
        userList.forEach(v -> {
            result.add(new ModelMapper().map(v,ResponseOrder.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
