package com.niklamix.graduateworkrest.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.niklamix.graduateworkrest.serializer.DataJsonDeserializer;
import com.niklamix.graduateworkrest.serializer.DataJsonSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class EntityDetailDTO {
    private long id;
    @JsonSerialize(using = DataJsonSerializer.class)
    @JsonDeserialize(using = DataJsonDeserializer.class)
    private LocalDateTime createdDate;
    private String createdBy;
    @JsonSerialize(using = DataJsonSerializer.class)
    @JsonDeserialize(using = DataJsonDeserializer.class)
    private LocalDateTime lastModifiedDate;
    private String lastModifiedBy;
}
