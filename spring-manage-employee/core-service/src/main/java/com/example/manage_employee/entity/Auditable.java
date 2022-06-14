package com.example.manage_employee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public abstract class Auditable<T> implements Serializable {
    @Column(name = "created_by", updatable = false)
    @CreatedBy
    protected T createdBy;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    protected LocalDateTime createdDate;

    @LastModifiedBy
    protected T lastModifiedBy;

    @LastModifiedDate
    protected LocalDateTime lastModifieDate;
}
