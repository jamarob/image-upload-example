package de.jamarob.photowall.controller;

import de.jamarob.photowall.model.Photo;
import de.jamarob.photowall.service.CloudinaryService;
import de.jamarob.photowall.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("photo")
public class PhotoController {

    private final PhotoService photoService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public PhotoController(PhotoService photoService, CloudinaryService cloudinaryService) {
        this.photoService = photoService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping
    public List<Photo> findAll(){
        return photoService.findAll();
    }

    @PostMapping("upload")
    public Photo uploadImage(@RequestParam MultipartFile image) throws IOException {
        File fileToUpload = File.createTempFile("photo", null);
        image.transferTo(fileToUpload);
        Photo photoToSave = cloudinaryService.uploadImage(fileToUpload);
        return photoService.savePhoto(photoToSave);
    }

    @DeleteMapping("{id}")
    public void deletePhoto(@PathVariable String id) throws IOException {
        cloudinaryService.deletePhoto(id);
        photoService.deletePhoto(id);
    }
}
