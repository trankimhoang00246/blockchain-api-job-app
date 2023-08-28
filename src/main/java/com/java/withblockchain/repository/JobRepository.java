package com.java.withblockchain.repository;

import com.java.withblockchain.model.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByProject_Id(long id);
}
