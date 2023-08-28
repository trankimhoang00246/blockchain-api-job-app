package com.java.withblockchain.model.entity;

import com.java.withblockchain.model.dto.ContractDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private String address; // address của người tạo contract

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_db_contract_id::bigint")
    private FileDB fileDBContract; // file cv

    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Stage> stages = new ArrayList<>();

    @Column(name = "total_number_day_complete")
    private int totalNumberDayComplete; // tổng ngày sẽ hoàn thành Job

    private String status; // trạng thái: reservation / working / done

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    public static ContractDto toDto(Contract contract) {
        return ContractDto.builder()
                .id(contract.getId())
                .jobId(contract.getJob().getId())
                .address(contract.getAddress())
                .fileId(contract.getFileDBContract().getId())
                .description(contract.getDescription())
                .stageDtos(contract.getStages()
                        .stream()
                        .map(Stage::toDto)
                        .collect(Collectors.toList()))
                .totalNumberDayComplete(contract.getTotalNumberDayComplete())
                .status(contract.getStatus())
                .createAt(contract.getCreateAt())
                .updateAt(contract.getUpdateAt())
                .build();
    }
}
