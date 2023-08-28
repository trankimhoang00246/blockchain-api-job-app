package com.java.withblockchain.controller;

import com.java.withblockchain.model.form.ContractForm;
import com.java.withblockchain.model.form.JobForm;
import com.java.withblockchain.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @GetMapping("api/v1/contract/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {
        return new ResponseEntity(contractService.getById(id), HttpStatus.OK);
    }

    @PostMapping("api/v1/contract")
    public ResponseEntity save(@RequestParam Long jobId,
                               @RequestParam String description,
                               @RequestParam("file") MultipartFile file) { //file cv
        ContractForm contractForm = ContractForm.builder()
                .jobId(jobId)
                .description(description)
                .build();
        return new ResponseEntity(contractService.save(contractForm, file), HttpStatus.OK);
    }

    // lay list job cua project
    @GetMapping("api/v1/contract/my") // TODO: loi
    public ResponseEntity getMyContract() {
        return new ResponseEntity(contractService.getMyContract(), HttpStatus.OK);
    }

    @PostMapping("api/v1/contract/{contractId}/set-status")
    public ResponseEntity setStatus(@PathVariable("contractId") long contractId,
                                    @RequestParam("status") String status) {
        return new ResponseEntity(contractService.setStatus(contractId, status), HttpStatus.OK);
    }
}
