    package com.diet.backend.service;

    import com.diet.backend.exception.BadRequestException;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Service;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.UUID;

    @Service
    @RequiredArgsConstructor
    public class FileService {
        @Value("${file.direct}")
        private String fileDirection;

        public String saveFile(MultipartFile multipartFile){
            if (multipartFile.isEmpty()){
                throw new BadRequestException("Cannot save an empty file!");
            }
            try {
                Path uploadPath = Paths.get(fileDirection);
                if (!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }
                String originalFilename = multipartFile.getOriginalFilename();
                String fileName = UUID.randomUUID() + "_" + originalFilename;

                Path targetLocation = uploadPath.resolve(fileName);

                Files.copy(multipartFile.getInputStream(),targetLocation);

                return fileName;

            } catch (RuntimeException e) {
                throw new BadRequestException("Could not save file: "+e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
