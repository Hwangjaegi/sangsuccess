package com.green.sang.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FileDownload {
	
	private static final String DOWNLOAD_DIR = "/downloads/";
    private static final String FILE_NAME = "강사제안서_상상마당.zip";

    public static void main(String[] args) {
        SpringApplication.run(FileDownload.class, args);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile() throws MalformedURLException {
        Path filePath = Paths.get("src/main/resources/static" + DOWNLOAD_DIR + FILE_NAME);
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
