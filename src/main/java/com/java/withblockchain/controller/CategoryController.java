package com.java.withblockchain.controller;

import com.java.withblockchain.model.form.CategoryForm;
import com.java.withblockchain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    public final CategoryService categoryService;

    @GetMapping("api/v1/category/{id}")
    public ResponseEntity getById(@PathVariable("id") long id) {
        return new ResponseEntity(categoryService.getById(id), HttpStatus.OK);
    }

    @PostMapping("api/v1/category")
    public ResponseEntity save(@RequestParam("name") String name) {
        return new ResponseEntity(categoryService.save(name), HttpStatus.OK);
    }

    @GetMapping("api/v1/category")
    public ResponseEntity getAll() {
        return new ResponseEntity(categoryService.getAll(), HttpStatus.OK);
    }
}
