package com.java.withblockchain.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    MultipartFile getById(long id);
}
