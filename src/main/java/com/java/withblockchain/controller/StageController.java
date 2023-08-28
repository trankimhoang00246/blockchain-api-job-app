package com.java.withblockchain.controller;

import com.java.withblockchain.model.form.StageForm;
import com.java.withblockchain.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class StageController {
    private final StageService stageService;

    @GetMapping("api/v1/stage/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {
        return new ResponseEntity(stageService.getById(id), HttpStatus.OK);
    }

    @PostMapping("api/v1/stage")
    public ResponseEntity save(@RequestParam("contractId") Long contractId,
                               @RequestParam("phase") int phase,
                               @RequestParam("mission") String mission,
                               @RequestParam("description") String description,
                               @RequestParam("money") BigDecimal money,
                               @RequestParam("numberDayComplete") int numberDayComplete,

                               @RequestParam("file") MultipartFile file) {
        StageForm stageForm = StageForm.builder()
                .contractId(contractId)
                .phase(phase)
                .mission(mission)
                .description(description)
                .money(money)
                .numberDayComplete(numberDayComplete)
                .build();
        return new ResponseEntity(stageService.save(stageForm, file), HttpStatus.OK);
    }
}
