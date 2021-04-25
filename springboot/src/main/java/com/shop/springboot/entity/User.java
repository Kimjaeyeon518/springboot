package com.shop.springboot.entity;

import com.shop.springboot.dto.userDto.UserRequestDto;
import com.shop.springboot.dto.userDto.UserResponseDto;
import com.shop.springboot.entity.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity // 테이블과 링크될 클래스
@ToString
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column
    private String name;
    @Column
    private String addr;
    @Column
    private String detailAddr;
    @Column
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts = new ArrayList<Cart>();

    @Enumerated(EnumType.STRING)    // JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정 (기본은 int형)
    private Role role;

    public String getRoleKey() {
        return this.role.getKey();
    }

    public User updateProfile(UserRequestDto userRequestDto) {
        this.name = userRequestDto.getName();
        this.addr = userRequestDto.getAddr();
        this.detailAddr = userRequestDto.getDetailAddr();

        return this;
    }

    public UserResponseDto toResponseDto(User user) {

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .addr(user.getAddr())
                .detailAddr(user.getDetailAddr())
                .build();
    }
}
