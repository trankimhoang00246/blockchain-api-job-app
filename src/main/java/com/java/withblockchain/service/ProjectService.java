package com.java.withblockchain.service;

import com.java.withblockchain.model.dto.ProjectDto;
import com.java.withblockchain.model.form.ProjectForm;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProjectService {

    public ProjectDto save(ProjectForm projectForm);

    public ProjectDto getById(long id);

    public List<ProjectDto> getAll();
}
