package com.java.withblockchain.controller;

import com.java.withblockchain.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileDBController {
    private final FileService fileService;

    @GetMapping("api/v1/file/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {
        return new ResponseEntity(fileService.getById(id), HttpStatus.OK);
    }
}
