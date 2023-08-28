package com.java.withblockchain.service.impl;

import com.java.withblockchain.exception.ResourceNotFoundException;
import com.java.withblockchain.model.dto.CategoryDto;
import com.java.withblockchain.model.entity.Category;
import com.java.withblockchain.model.form.CategoryForm;
import com.java.withblockchain.repository.CategoryRepository;
import com.java.withblockchain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto getById(long id) {
        return Category.toDto(categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category", "id", id)
        ));
    }

    @Override
    public CategoryDto save(String name) {
        Category category = new Category();
        category.setName(name);
        return Category.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(Category::toDto)
                .collect(Collectors.toList());
    }

}
