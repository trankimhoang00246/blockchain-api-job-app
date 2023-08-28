package com.java.withblockchain.model.entity;

import com.java.withblockchain.model.dto.ProjectDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
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
@Table(name = "project")
public class Project{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address; // address của người tạo

    private String name;

    private String description;

    @Column(name = "link_git")
    private String linkGit;

    @Column(name = "link_twitter")
    private String linkTwitter;

    private String location;

    private BigDecimal money; // tổng tiền người tạo cho cho project

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> category = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Job> jobs = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    public static ProjectDto toDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .address(project.getAddress())
                .categoryDtos(project.getCategory().stream().map(Category::toDto).collect(Collectors.toList()))
                .description(project.getDescription())
                .linkGit(project.getLinkGit())
                .linkTwitter(project.getLinkTwitter())
                .location(project.getLocation())
                .name(project.getName())
                .money(project.getMoney())
                .createAt(project.getCreateAt())
                .updateAt(project.getUpdateAt())
                .build();
    }
}
