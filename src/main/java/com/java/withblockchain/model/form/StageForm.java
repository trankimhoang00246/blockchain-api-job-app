package com.java.withblockchain.model.form;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@Builder
public class StageForm {
    private Long contractId;
    private int phase;
    private String mission;
    private String description;
    private BigDecimal money;
    private int numberDayComplete;
    // private MultipartFile multipartFile;
}
