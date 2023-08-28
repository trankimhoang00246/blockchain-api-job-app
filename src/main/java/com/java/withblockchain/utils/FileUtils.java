package com.java.withblockchain.utils;

import com.java.withblockchain.model.entity.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUtils {
    public static FileDB toFile(MultipartFile multipartFile) {
        try {
            return FileDB.builder()
                    .name(multipartFile.getOriginalFilename())
                    .type(multipartFile.getContentType())
                    .data(multipartFile.getBytes())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static MultipartFile toMultipartFile(FileDB fileDB){
        return new MockMultipartFile(
                "file", // parameter name
                fileDB.getName(), // original filename
                fileDB.getType(), // content type
                fileDB.getData() // file content as byte array
        );

    }
}
