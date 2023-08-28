package com.java.withblockchain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String name;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
