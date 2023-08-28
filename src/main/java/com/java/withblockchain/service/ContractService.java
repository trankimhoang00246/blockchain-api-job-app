package com.java.withblockchain.service;

import com.java.withblockchain.model.dto.ContractDto;
import com.java.withblockchain.model.form.ContractForm;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ContractService {

    ContractDto save(ContractForm contractForm, MultipartFile file);
    ContractDto getById(long id);
    List<ContractDto> getMyContract();

    ContractDto setStatus(long contractId, String status);
}
