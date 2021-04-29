package com.shop.springboot.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    OUTER("ROLE_OUTER", "아우터"),
    TOPLONG("ROLE_TOPLONG", "긴팔상의"),
    TOPSHORT("ROLE_TOPSHORT", "반팔상의"),
    PANTSLONG("ROLE_PANTSLONG", "긴바지"),
    PANTSSHORT("ROLE_PANTSSHORT", "반바지"),
    SHOES("ROLE_SHOES", "신발"),
    ACC("ROLE_ACC", "악세서리");

    private final String key;
    private final String title;
}

