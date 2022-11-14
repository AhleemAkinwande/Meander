package org.launchcode.tripproject.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// TODO Figure out why code is not allowing file to be uploaded
// TODO Connect to index.html

@Controller
@ResponseBody
public class PhotoUploadController {
    //
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    @GetMapping("/uploadimage") public String displayUploadForm() { // The displayUploadForm() method handles the GET request and returns the name of the Thymeleaf template to display to the user in order to let them import an image.
        return "imageupload/index";
    }

    // The uploadImage() method handles the image upload. It accepts a multipart/form-data POST request on image upload and saves the image on the local file system. The MultipartFile interface is a special data structure that Spring Boot provides to represent an uploaded file in a multipart request.
    @PostMapping("/upload") public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
        return "imageupload/index";
    }
}
