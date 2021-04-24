package com.shop.springboot.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor  // 기본 생성자
@Getter
@Setter
@Entity // 테이블과 링크될 클래스
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false, unique = true)
    private String email;

    private String name;
    private String addr;
    private String detailAddr;
    private String password;

}
