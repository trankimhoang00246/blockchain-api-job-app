package com.java.withblockchain.service.impl;

import com.java.withblockchain.exception.ResourceNotAccessException;
import com.java.withblockchain.exception.ResourceNotFoundException;
import com.java.withblockchain.model.entity.Contract;
import com.java.withblockchain.model.entity.FileDB;
import com.java.withblockchain.model.entity.Stage;
import com.java.withblockchain.repository.FileDBRepository;
import com.java.withblockchain.service.FileService;
import com.java.withblockchain.utils.FileUtils;
import com.java.withblockchain.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileDBRepository fileDBRepository;

    @Override
    public MultipartFile getById(long id) {
        FileDB fileDB = fileDBRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("File", "id", id));

        if(fileDB.getContract() != null) {
            Contract contract = fileDB.getContract();
            // chỉ có người tạo job và người tạo contract mới có thể xem
            if(contract.getAddress().equals(SecurityUtils.getAddressOfPrincipal()) ||
                    contract.getJob().getProject().getAddress().equals(SecurityUtils.getAddressOfPrincipal())) {
                return FileUtils.toMultipartFile(fileDB);
            }

        } else {
            Stage stage = fileDB.getStage();
            Contract contract = stage.getContract();
            // chỉ có người tạo job và người tạo contract mới có thể xem stage
            if(contract.getAddress().equals(SecurityUtils.getAddressOfPrincipal()) ||
                    contract.getJob().getProject().getAddress().equals(SecurityUtils.getAddressOfPrincipal())) {
                return FileUtils.toMultipartFile(fileDB);
            }
        }

        throw new ResourceNotAccessException();
    }
}
