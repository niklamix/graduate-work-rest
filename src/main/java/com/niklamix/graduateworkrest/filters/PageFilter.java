package com.niklamix.graduateworkrest.filters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
public class PageFilter {
    private int page = 0;
    private int size = 10;
    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(getPage(), getSize());
    }
}