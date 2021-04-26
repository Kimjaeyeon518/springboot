package com.shop.springboot.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    OUTER("ROLE_OUTER", "아우터"),
    LONGTOP("ROLE_LONGTOP", "긴팔상의"),
    SHORTTOP("ROLE_SHORTTOP", "반팔상의"),
    LONGPants("ROLE_LONGTOP", "긴바지"),
    SHORTPants("ROLE_SHORTTOP", "반바지"),
    SHOES("ROLE_SHOES", "신발"),
    ACC("ROLE_ACC", "악세서리");

    private final String key;
    private final String title;
}

