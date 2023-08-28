package com.java.withblockchain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ContractDto {
    private Long id;
    private Long jobId;
    private String address; // address của người tạo contract

    //private MultipartFile multipartFile; //file cv
    private Long fileId;

    private String description;
    private List<StageDto> stageDtos;
    private int totalNumberDayComplete;
    private String status;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
