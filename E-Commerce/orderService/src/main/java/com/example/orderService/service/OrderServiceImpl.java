package com.example.orderService.service;

import com.example.orderService.dto.OrderDto;
import com.example.orderService.jpa.OrderEntity;
import com.example.orderService.jpa.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.UUID;

/* Service를 상속받은 ServiceImpl
   @Service 를 이용해 서비스 로직을 처리하는 것을 알려준다
   여기에서 비즈니스 로직 처리를 한다
   UserDto에 랜덤 ID를 추가하고, UserEntity로 변환한다
   이후 이를 DB에 저장하도록 Repository로 전달
   return 할 때는 UserDto로 값을 다시 변환해서 보내도록 한다
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    OrderRepository repository;
    Environment env;

    @Autowired
    public OrderServiceImpl(OrderRepository repository,
                            Environment env){
        this.repository = repository;
        this.env = env;
    }
    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.setOrderId(UUID.randomUUID().toString());
        orderDetails.setTotalPrice(orderDetails.getQty()*orderDetails.getUnitPrice());
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderEntity orderEntity = mapper.map(orderDetails, OrderEntity.class);

        repository.save(orderEntity);

        OrderDto returnValue = mapper.map(orderEntity, OrderDto.class);
        return returnValue;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId){
        OrderEntity orderEntity = repository.findByOrderId(orderId);
        OrderDto orderDto = new ModelMapper().map(orderEntity, OrderDto.class);
        //주문서비스
        return orderDto;
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUserId(String userId){
        return repository.findByUserId(userId);
    }
}


