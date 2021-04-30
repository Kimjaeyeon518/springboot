package com.shop.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.springboot.dto.MeDto.MeRequestDto;
import com.shop.springboot.dto.userDto.UserRequestDto;
import com.shop.springboot.dto.userDto.UserResponseDto;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String identifier;
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
    @Column
    private String authorities;
    @Column
    private Character disabledYn;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cart> carts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProductOrder> productOrders;

    public User(User user) {
        this.id = user.getId();
        this.identifier = user.getIdentifier();
        this.email = user.getEmail();
        this.name = user.getName();
        this.addr = user.getAddr();
        this.detailAddr = user.getDetailAddr();
        this.authorities = user.getAuthorities();
        this.productOrders = user.getProductOrders();
    }

    public UserResponseDto toResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .identifier(user.getIdentifier())
                .name(user.getName())
                .email(user.getEmail())
                .addr(user.getAddr())
                .detailAddr(user.getDetailAddr())
                .build();
    }

    public User deleteUser() {
        this.disabledYn = 'Y';

        return this;
    }

    public User updateProfiles(MeRequestDto meRequestDto) {
        this.name = meRequestDto.getName();
        this.email = meRequestDto.getEmail();
        this.addr = meRequestDto.getAddr();
        this.detailAddr = meRequestDto.getDetailAddr();

        return this;
    }

    public User updatePassword(String password) {
        this.password = password;

        return this;
    }
}
