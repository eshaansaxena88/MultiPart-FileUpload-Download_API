package com.example.multiPartFormData.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;

@Controller
public class FileDownloadController {

	@Value("${file.download-dir}")
	private String downloadDir;
	
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        // Provide the path to the directory where the files are stored
        String directory = downloadDir;

        // Construct the file path
        String filePath = directory + File.separator + fileName;

        // Create a FileSystemResource from the file path
        Resource resource = new FileSystemResource(filePath);

        // Check if the file exists
        if (resource.exists()) {
            // Set the content type and attachment header
            HttpHeaders headers = new HttpHeaders();
           
            //below line will send the attachement as it is and it will be downloaded genrically
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            
            //below line will help in download as well as stream only video.
           // headers.setContentType(MediaType.parseMediaType("video/mp4"));
            headers.setContentDispositionFormData("attachment", fileName);

            // Return the file as a ResponseEntity with appropriate headers
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } else {
            // Return a 404 Not Found response if the file doesn't exist
            return ResponseEntity.notFound().build();
        }
    }

}