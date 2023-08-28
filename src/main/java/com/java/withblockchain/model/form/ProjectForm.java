package com.java.withblockchain.model.form;

import com.java.withblockchain.model.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProjectForm {// address của người tạo

    private String name;
    private String description;
    private String linkGit;
    private String linkTwitter;
    private String location;
    private List<Long> categoryId; //
}
