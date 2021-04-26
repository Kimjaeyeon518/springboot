package com.shop.springboot.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductOrderStatus {

    ARRIVE("ROLE_ARRIVE", "배송완료"),
    WAIT("ROLE_WAIT", "배송중");

    private final String key;
    private final String title;
}
