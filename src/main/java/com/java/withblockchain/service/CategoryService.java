package com.java.withblockchain.service;

import com.java.withblockchain.model.dto.CategoryDto;
import com.java.withblockchain.model.form.CategoryForm;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    public CategoryDto getById(long id);

    public CategoryDto save(String name);

    public List<CategoryDto> getAll();

}
