package com.shop.springboot.entity;

import javax.persistence.*;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false)
    protected LocalDateTime createdTime;

    @Column
    protected LocalDateTime updatedTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = this.updatedTime = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = LocalDateTime.now();
    }
}