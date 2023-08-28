package com.java.withblockchain.service;

import com.java.withblockchain.model.dto.StageDto;
import com.java.withblockchain.model.form.StageForm;
import org.springframework.web.multipart.MultipartFile;

public interface StageService {
    StageDto save(StageForm stageForm, MultipartFile file);
    StageDto getById(long id);
}
