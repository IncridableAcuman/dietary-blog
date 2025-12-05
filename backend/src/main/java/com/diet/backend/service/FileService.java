package com.diet.backend.service;

import com.diet.backend.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@RequiredArgsConstructor
public class FileService {
    @Value("${file.direct}")
     final private String fileDirection;

    public String saveFile(MultipartFile multipartFile){
        try {
            String fileName = multipartFile.getOriginalFilename();
            File directory = new File(fileDirection);
            if (!directory.exists()){
                directory.mkdirs();
            }
            File save = new File(directory.getParentFile() + File.separator + fileName);
            return save.getName();
        } catch (RuntimeException e) {
            throw new BadRequestException("Could not save file");
        }
    }
}
