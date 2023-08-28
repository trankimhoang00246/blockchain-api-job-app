package com.java.withblockchain.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class StageDto {
    private Long id;
    private int phase;
    private String mission;
    private String description;
    private BigDecimal money;
    private int numberDayComplete;


    // private MultipartFile multipartFile;
    private Long fileId;

    private Date deadline;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
