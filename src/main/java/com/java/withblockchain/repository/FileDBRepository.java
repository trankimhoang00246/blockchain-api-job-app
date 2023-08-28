package com.java.withblockchain.repository;

import com.java.withblockchain.model.entity.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDBRepository extends JpaRepository<FileDB, Long> {
}
