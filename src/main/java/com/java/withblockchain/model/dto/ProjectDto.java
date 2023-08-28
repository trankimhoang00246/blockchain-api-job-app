package com.java.withblockchain.model.dto;

import com.java.withblockchain.model.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProjectDto {
    private Long id;
    private String address; // address của người tạo
    private String name;
    private String description;
    private String linkGit;
    private String linkTwitter;
    private String location;
    private BigDecimal money; // tổng tiền người tạo cho cho project
    private List<CategoryDto> categoryDtos;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
