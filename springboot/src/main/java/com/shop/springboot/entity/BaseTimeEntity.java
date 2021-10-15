package com.shop.springboot.entity;

import javax.persistence.*;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // JPA Entity 클래스들이 해당 추상클래스를 상속할 경우 createdDate, modifiedDate을 컬럼으로 인식
@EntityListeners(AuditingEntityListener.class)  // 해당 클래스에 Auditing 기능을 포함 ( Auditing : 감시 기능 -> Entity의 C,R 시간 자동저장 )
public abstract class BaseTimeEntity {

    @CreatedDate
    protected LocalDateTime createdTime;

    @LastModifiedDate
    protected LocalDateTime updatedTime;

}