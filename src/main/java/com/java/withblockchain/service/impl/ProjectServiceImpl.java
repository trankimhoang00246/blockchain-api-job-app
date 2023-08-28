package com.java.withblockchain.service.impl;

import com.java.withblockchain.exception.ResourceNotFoundException;
import com.java.withblockchain.model.dto.ProjectDto;
import com.java.withblockchain.model.entity.Category;
import com.java.withblockchain.model.entity.Project;
import com.java.withblockchain.model.form.ProjectForm;
import com.java.withblockchain.repository.CategoryRepository;
import com.java.withblockchain.repository.ProjectRepository;
import com.java.withblockchain.service.ProjectService;
import com.java.withblockchain.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final CategoryRepository categoryRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ProjectDto save(ProjectForm projectForm) {

        // TODO: connect smart contract to create contract

        Project project = Project.builder()
                .address(SecurityUtils.getAddressOfPrincipal())
                .name(projectForm.getName())
                .linkGit(projectForm.getLinkGit())
                .linkTwitter(projectForm.getLinkTwitter())
                .money(new BigDecimal("0"))
                .location(projectForm.getLocation())
                .category(projectForm.getCategoryId()
                        .stream()
                        .map(id
                                -> categoryRepository.findById(id)
                                .orElseThrow(()
                                        -> new ResourceNotFoundException("Category", "id", id)))
                        .collect(Collectors.toList()))
                .description(projectForm.getDescription())
                .build();

        return Project.toDto(projectRepository.save(project));
    }

    @Override
    public ProjectDto getById(long id) {
        return Project.toDto(projectRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Project", "id", id)));
    }

    @Override
    public List<ProjectDto> getAll() {
        return projectRepository.findAll()
                .stream()
                .map(Project::toDto)
                .collect(Collectors.toList());
    }
}
