package de.jamarob.photowall.controller;

import de.jamarob.photowall.model.Photo;
import de.jamarob.photowall.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("photo")
public class PhotoController {

    private final PhotoService service;

    @Autowired
    public PhotoController(PhotoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Photo> findAll(){
        return service.findAll();
    }

}
