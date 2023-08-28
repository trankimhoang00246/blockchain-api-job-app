package com.java.withblockchain.model.form;

import lombok.Data;

@Data
public class JobForm {
    private long projectId;
    private String name;
    private String description;
    private float money;
}
