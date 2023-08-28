package com.java.withblockchain.controller;

import com.java.withblockchain.model.form.JobForm;
import com.java.withblockchain.model.form.ProjectForm;
import com.java.withblockchain.repository.JobRepository;
import com.java.withblockchain.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("api/v1/job/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {
        return new ResponseEntity(jobService.getById(id), HttpStatus.OK);
    }

    @PostMapping("api/v1/job")
    public ResponseEntity save(@RequestBody JobForm jobForm) {
        return new ResponseEntity(jobService.save(jobForm), HttpStatus.OK);
    }

    // lay list job cua project
    @GetMapping("api/v1/job")
    public ResponseEntity getAllByProjectId(@RequestParam("projectId") long projectId) {
        return new ResponseEntity(jobService.getAllByProjectId(projectId), HttpStatus.OK);
    }
}
