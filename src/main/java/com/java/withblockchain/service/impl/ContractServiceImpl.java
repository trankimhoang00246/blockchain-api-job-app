package com.java.withblockchain.service.impl;

import com.java.withblockchain.exception.ResourceNotAccessException;
import com.java.withblockchain.exception.ResourceNotFoundException;
import com.java.withblockchain.model.dto.ContractDto;
import com.java.withblockchain.model.entity.Contract;
import com.java.withblockchain.model.form.ContractForm;
import com.java.withblockchain.repository.ContractRepository;
import com.java.withblockchain.repository.JobRepository;
import com.java.withblockchain.service.ContractService;
import com.java.withblockchain.utils.FileUtils;
import com.java.withblockchain.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ContractServiceImpl implements ContractService {
    private final JobRepository jobRepository;
    private final ContractRepository contractRepository;

    // TODO: connect smart contract to change status contract

    @Override
    public ContractDto save(ContractForm contractForm, MultipartFile file) {
        
        Contract contract = Contract.builder()
                .job(jobRepository.findById(contractForm.getJobId())
                        .orElseThrow(()->
                                new ResourceNotFoundException("Job", "id", contractForm.getJobId())))
                .address(SecurityUtils.getAddressOfPrincipal())
                .fileDBContract(FileUtils.toFile(file))
                .description(contractForm.getDescription())
                .totalNumberDayComplete(0)
                .stages(new ArrayList<>())
                .status("reservation")
                .build();

        return Contract.toDto(contractRepository.save(contract));
    }

    @Override
    public ContractDto getById(long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Contract", "id", id));

        // chỉ có người tạo job và người tạo contract mới có thể xem
        if(contract.getAddress().equals(SecurityUtils.getAddressOfPrincipal()) ||
                contract.getJob().getProject().getAddress().equals(SecurityUtils.getAddressOfPrincipal())) {
            return Contract.toDto(contract);
        }

        throw new ResourceNotAccessException();
    }

    @Override
    public List<ContractDto> getMyContract() {
        return contractRepository.findByAddress(SecurityUtils.getAddressOfPrincipal())
                .stream()
                .map(contract -> Contract.toDto(contract))
                .collect(Collectors.toList());
    }

    @Override
    public ContractDto setStatus(long contractId, String status) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Contract", "id", contractId));

        /*
            Th1: Pending -> Progress, Progress -> Finished
            set job và contract thành status mới như bình thường
            Th2: Progress -> Failed
            contract set Failed, job set Pending
         */
        // chỉ có người tạo job mới có thể set status
        if(contract.getJob().getProject().getAddress().equals(SecurityUtils.getAddressOfPrincipal())) {
            if(status.equals("Failed")) {
                contract.setStatus("Failed");
                contract.getJob().setStatus("Pending");
            } else if(status.equals("Progress") || status.equals("Finished")) {
                contract.setStatus(status);
                contract.getJob().setStatus(status);
            }

            return Contract.toDto(contractRepository.save(contract));
        }
        throw new ResourceNotAccessException();
    }
}
