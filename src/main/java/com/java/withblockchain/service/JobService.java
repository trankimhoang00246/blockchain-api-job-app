package com.java.withblockchain.service;

import com.java.withblockchain.model.dto.JobDto;
import com.java.withblockchain.model.form.JobForm;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JobService {
    JobDto save(JobForm jobForm);
    JobDto getById(long id);
    List<JobDto> getAllByProjectId(long projectId);
}
