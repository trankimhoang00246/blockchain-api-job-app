package com.java.withblockchain.controller;

import com.java.withblockchain.model.form.CategoryForm;
import com.java.withblockchain.model.form.ProjectForm;
import com.java.withblockchain.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("api/v1/project/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {
        return new ResponseEntity(projectService.getById(id), HttpStatus.OK);
    }

    @PostMapping("api/v1/project")
    public ResponseEntity save(@RequestBody ProjectForm projectForm) {
        return new ResponseEntity(projectService.save(projectForm), HttpStatus.OK);
    }

    @GetMapping("api/v1/project")
    public ResponseEntity getAll() {
        return new ResponseEntity(projectService.getAll(), HttpStatus.OK);
    }
}
