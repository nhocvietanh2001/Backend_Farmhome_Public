package com.ute.farmhome.utility.implement;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.ute.farmhome.dto.FileUpload;
import com.ute.farmhome.utility.UpdateFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class UpdateFileImplement implements UpdateFile {

    @Autowired
    ServletContext application;

    @Autowired
    Storage storage;

    String bucketName = "farmhome";

    @Override
    public void update(FileUpload fileUpload) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            fileUpload.setOutput(LocalDateTime.now().format(formatter) +
                    fileUpload.getFile().getOriginalFilename().substring(fileUpload.getFile().getOriginalFilename().lastIndexOf(".")));

            Credentials credentials = GoogleCredentials.fromStream(new ClassPathResource("key.json").getInputStream());
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

            String folderName =  "image/";
            BlobId blobId = BlobId.of("farmhome",folderName + fileUpload.getOutput());
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(fileUpload.getFile().getContentType()).build();
            byte[] arr = fileUpload.getFile().getBytes();
            storage.create(blobInfo, arr);

            fileUpload.setOutput("https://storage.googleapis.com/" + bucketName + "/" + folderName +fileUpload.getOutput());
            fileUpload.setFile(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String fullPath) {
        try {
            String name = fullPath.substring(fullPath.lastIndexOf("/") + 1);

            Credentials credentials = GoogleCredentials.fromStream(new ClassPathResource("key.json").getInputStream());
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

            storage.delete(bucketName, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
