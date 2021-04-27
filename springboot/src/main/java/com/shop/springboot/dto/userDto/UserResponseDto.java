package com.shop.springboot.dto.userDto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseDto {

    private Long id;
    private String identifier;
    private String email;
    private String name;
    private String addr;
    private String detailAddr;

}
