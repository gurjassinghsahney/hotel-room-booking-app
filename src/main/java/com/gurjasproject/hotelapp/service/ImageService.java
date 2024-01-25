package com.gurjasproject.hotelapp.service;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

//Beans are objects under the management of spring framework.
//We use them because they have built in support for dependency injection

    //Dependency Injection is a programming technique in
    //which an object or function receives other objects
    //or functions that it requires, as opposed to creating them internally.

    //Dependency injection aims to separate the concerns of constructing objects
    //and using them, leading to loosely coupled programs

@Service
//We use @service, @component, @repository etc. for dependency injection support
public class ImageService{
//We will put our Functions here:-

    //A method to convert the MultipartFile into to File Object.
    //we declare private because it's not supposed to be an important function
    //it's just a tool
    private File convertToFile(
            MultipartFile multipartFile, //representation of an uploaded file
            String fileName
    ) throws IOException{   //in-case file is not found
        File tempFile = new File(fileName);
        try(
                FileOutputStream fos = new FileOutputStream((tempFile))){
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    //This method will upload the file into the firebase cloud storage
    private String uploadFile(
            File file,
            String fileName
    ) throws IOException{
        //BlobId is google storage object identifier
        BlobId blobId = BlobId.of("hotel-dc0ec.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        InputStream inputStream = ImageService.class.getClassLoader().getResourceAsStream("firebaseprivatekey.json"); // change the file name with your one
        Credentials credentials = GoogleCredentials.fromStream(inputStream);
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/<bucket-name>/o/%s?alt=media";
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    public String upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();
            return URL;
        } catch (Exception e) {
            e.printStackTrace();
            return "Image couldn't upload, Something went wrong";
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


}
