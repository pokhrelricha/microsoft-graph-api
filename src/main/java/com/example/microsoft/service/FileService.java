package com.example.microsoft.service;

import org.springframework.core.io.InputStreamResource;

/**
 * @author Richa Pokhrel
 */
public interface FileService {

    /**
     * Downloads a file from OneDrive using the file ID.
     *
     * @param fileId The ID of the file to download.
     * @return The file content as an InputStreamResource.
     */
    InputStreamResource downloadFile(String fileId);

}
