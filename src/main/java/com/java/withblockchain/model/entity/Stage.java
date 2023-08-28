package com.java.withblockchain.model.entity;

import com.java.withblockchain.model.dto.StageDto;
import com.java.withblockchain.utils.FileUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

// lập giai đoạn
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "stage")
public class Stage{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int phase; //stt giai đoạn

    private String mission;

    private String description;

    private BigDecimal money; // số tiền mong muốn ở giai đoạn này

    @Column(name = "number_day_complete")
    private int numberDayComplete; // dự kiến ngày hoàn thành giai đoạn này

    private Date deadline;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_db_contract_id::bigint")
    private FileDB fileDBStage; // file nop cho giai doan nay

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    public static StageDto toDto(Stage stage) {
        return StageDto.builder()
                .id(stage.getId())
                .phase(stage.getPhase())
                .mission(stage.getMission())
                .description(stage.getDescription())
                .money(stage.getMoney())
                .numberDayComplete(stage.getNumberDayComplete())
                .fileId(stage.getFileDBStage().getId())
                .deadline(stage.getDeadline())
                .createAt(stage.getCreateAt())
                .updateAt(stage.getUpdateAt())
                .build();
    }
}
