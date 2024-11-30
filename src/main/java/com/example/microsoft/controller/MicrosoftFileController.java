package com.example.microsoft.controller;

import com.example.microsoft.service.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richa Pokhrel
 */
@RestController
@RequestMapping("/api/files")
public class MicrosoftFileController {

    private final FileService fileService;

    public MicrosoftFileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Endpoint to download a file from OneDrive.
     *
     * @param fileId The ID of the file to download.
     * @return A {@link ResponseEntity} containing the file content as a download stream.
     */
    @GetMapping(value = "/download/{fileId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileId) {
        try {
            InputStreamResource fileContent = fileService.downloadFile(fileId);

            // Set response headers for file download
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileContent.getDescription() + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(fileContent);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
