package com.java.withblockchain.model.dto;

import com.java.withblockchain.model.entity.Project;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class JobDto {
    private Long id;
    private String name;
    private String description;
    private float money;
    private String status;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
