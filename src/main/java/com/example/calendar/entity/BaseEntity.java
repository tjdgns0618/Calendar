package com.example.calendar.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// MappedSuperclass의 역할 : 모든 데이터 테이블 무조건 공통적으로 들어가야하는
// Auditing필드들을 한곳에 모아둘때 사용하는 어노테이션
@MappedSuperclass
// 해당 엔티티에 데이터가 저장되거나 수정되는 타이밍을 감지해서,
// 특정 동작을 자동으로 수행하게 해주는 감시자를 붙여주는 역할
// BaseEntity를 상속받는 모든 곳에서 무조건 시간기록, 수정기록 AuditingEntityListener를 하나씩 붙이라는 뜻
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    // 이 필드를 포함하는 엔티티가 생성된 날짜를 나타내는 필드로 선언해준다.
    @CreatedDate
    // updatable 속성 : SQL UPDATE문에 필드가 포함되는지 여부
    // 변경이 있을시 UPDATE를 안해준다.
    // default = true 상태
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // 이 필드를 포함하는 엔티티가 수정된 날짜를 나타내는 필드로 선언해준다.
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
