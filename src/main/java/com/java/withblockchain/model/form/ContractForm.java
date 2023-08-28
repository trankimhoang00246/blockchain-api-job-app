package com.java.withblockchain.model.form;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class ContractForm {
    private Long jobId;
    // private MultipartFile multipartFile; //file cv
    private String description;
    // private List<StageForm> stageForms;
}
