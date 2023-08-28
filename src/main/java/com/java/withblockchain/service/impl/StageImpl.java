package com.java.withblockchain.service.impl;

import com.java.withblockchain.exception.ResourceNotAccessException;
import com.java.withblockchain.exception.ResourceNotFoundException;
import com.java.withblockchain.model.dto.StageDto;
import com.java.withblockchain.model.entity.Contract;
import com.java.withblockchain.model.entity.FileDB;
import com.java.withblockchain.model.entity.Stage;
import com.java.withblockchain.model.form.StageForm;
import com.java.withblockchain.repository.ContractRepository;
import com.java.withblockchain.repository.StageRepository;
import com.java.withblockchain.service.StageService;
import com.java.withblockchain.utils.FileUtils;
import com.java.withblockchain.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class StageImpl implements StageService {
    private final ContractRepository contractRepository;
    private final StageRepository stageRepository;

    @Override
    public StageDto save(StageForm stageForm, MultipartFile file) {
        Contract contract = contractRepository.findById(stageForm.getContractId())
                .orElseThrow(()->
                        new ResourceNotFoundException("Contract", "id", stageForm.getContractId()));

        // chỉ có người tạo hợp đồng mới có thể thêm stage
        if(contract.getAddress().equals(SecurityUtils.getAddressOfPrincipal())) {
            FileDB fileDB = FileUtils.toFile(file);

            Stage stage = Stage.builder()
                    .contract(contract)
                    .phase(stageForm.getPhase())
                    .mission(stageForm.getMission())
                    .description(stageForm.getDescription())
                    .money(stageForm.getMoney())
                    .numberDayComplete(stageForm.getNumberDayComplete())
                    .fileDBStage(fileDB)
                    .build();

            contract.setTotalNumberDayComplete(contract.getTotalNumberDayComplete() + stage.getNumberDayComplete());
            contract.getStages().add(stage);

            return Stage.toDto(stageRepository.save(stage));
        }

        throw new ResourceNotAccessException();
    }

    @Override
    public StageDto getById(long id) {
        Stage stage =stageRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Stage", "id", id));
        Contract contract = stage.getContract();
        // chỉ có người tạo job và người tạo contract mới có thể xem stage
        if(contract.getAddress().equals(SecurityUtils.getAddressOfPrincipal()) ||
                contract.getJob().getProject().getAddress().equals(SecurityUtils.getAddressOfPrincipal())) {
            return stage.toDto(stage);
        }

        throw new ResourceNotAccessException();
    }
}
