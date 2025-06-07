package com.myweb.weblaptop.service;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UploadService {
    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleUploadFile(MultipartFile file, String targetFolder) {
        if(file.isEmpty()) {
            return "";
        }

        String uploadRootPath = System.getProperty("user.dir") + "/src/main/resources/static/Images";
        String finalName = "";

        try {
            byte[] bytes = file.getBytes();

            File dir = new File(uploadRootPath + File.separator + targetFolder);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            finalName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace(); // Trong production nên dùng logger
        }

        return finalName;
    }

}
