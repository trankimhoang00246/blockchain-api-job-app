package com.java.withblockchain.repository;

import com.java.withblockchain.model.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByAddress(String address);
}
