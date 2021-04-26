package com.shop.springboot.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {

    SOLDOUT("ROLE_SOLDOUT", "품절"),
    SALE("ROLE_SALE", "판매중");

    private final String key;
    private final String title;
}
