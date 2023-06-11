package com.niklamix.graduateworkrest.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.niklamix.graduateworkrest.serializer.DataJsonDeserializer;
import com.niklamix.graduateworkrest.serializer.DataJsonSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
@Setter
@NoArgsConstructor
public class EntityDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CreatedDate
    @JsonSerialize(using = DataJsonSerializer.class)
    @JsonDeserialize(using = DataJsonDeserializer.class)
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @LastModifiedDate
    @JsonSerialize(using = DataJsonSerializer.class)
    @JsonDeserialize(using = DataJsonDeserializer.class)
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;
    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
}
