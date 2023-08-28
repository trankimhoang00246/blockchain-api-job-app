package com.java.withblockchain.service.impl;

import com.java.withblockchain.exception.ResourceNotAccessException;
import com.java.withblockchain.exception.ResourceNotFoundException;
import com.java.withblockchain.model.dto.JobDto;
import com.java.withblockchain.model.entity.Job;
import com.java.withblockchain.model.entity.Project;
import com.java.withblockchain.model.form.JobForm;
import com.java.withblockchain.repository.JobRepository;
import com.java.withblockchain.repository.ProjectRepository;
import com.java.withblockchain.service.JobService;
import com.java.withblockchain.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ProjectRepository projectRepository;
    @Override
    public JobDto save(JobForm jobForm) {
        Project project = projectRepository.findById(jobForm.getProjectId())
                .orElseThrow(()->
                        new ResourceNotFoundException("Project", "id", jobForm.getProjectId()));

        // kiểm tra người tạo job có phải là chủ của project không
        if(!project.getAddress().equals(SecurityUtils.getAddressOfPrincipal())) {
            throw new ResourceNotAccessException();
        }

        Job job = Job.builder()
                .project(project)
                .name(jobForm.getName())
                .description(jobForm.getDescription())
                .money(jobForm.getMoney())
                .status("reservation")  // TODO: khi ky hop dong chuyen trang thai
                .contract(new ArrayList<>())
                .build();
        return Job.toDto(jobRepository.save(job));
    }

    @Override
    public JobDto getById(long id) {
        return Job.toDto(jobRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Job","id",id))
        );
    }

    @Override
    public List<JobDto> getAllByProjectId(long projectId) {
        return jobRepository.findByProject_Id(projectId)
                .stream()
                .map(Job::toDto)
                .collect(Collectors.toList());
    }
}
