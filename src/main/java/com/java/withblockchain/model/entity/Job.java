package com.java.withblockchain.model.entity;


import com.java.withblockchain.model.dto.JobDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "job")
public class Job{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private String name;

    private String description;

    private float money;

    private String status; // trạng thái: reservation / working / done

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Contract> contract = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    public static JobDto toDto(Job job) {
        return JobDto.builder()
                .id(job.getId())
                .name(job.getName())
                .description(job.getDescription())
                .money(job.getMoney())
                .status(job.getStatus())
                .createAt(job.getCreateAt())
                .updateAt(job.getUpdateAt())
                .build();
    }
}
