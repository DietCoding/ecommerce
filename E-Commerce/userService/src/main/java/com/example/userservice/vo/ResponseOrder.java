package com.example.userservice.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrder {
    private String ProductId;
    private Integer Qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Date createAt; //주문날짜 Date는 util 패키지에 있는거 import

    private String orderId; //주문 아이디
}
