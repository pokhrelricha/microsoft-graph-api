package com.example.microsoft.service;

import com.microsoft.graph.models.DriveItem;
import com.microsoft.graph.requests.GraphServiceClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author Richa Pokhrel
 */
@Service
public class FileServiceImpl implements FileService {


    private final GraphServiceClient<?> graphClient;

    public FileServiceImpl(GraphServiceClient<?> graphClient) {
        this.graphClient = graphClient;
    }

    @Override
    public InputStreamResource downloadFile(String fileId) {
        try {
            // Step 1: Fetch file metadata
            DriveItem fileItem = graphClient.me().drive().items(fileId)
                    .buildRequest()
                    .get();

            // Step 2: Retrieve file content as InputStream
            InputStream fileContentStream = graphClient.me().drive().items(fileId).content()
                    .buildRequest()
                    .get();

            // Return the file content as InputStreamResource
            return new InputStreamResource(fileContentStream, fileItem.name);
        } catch (Exception e) {
            // Handle specific errors (log or rethrow)
            throw new RuntimeException("Error downloading file: " + e.getMessage(), e);
        }
    }
}
