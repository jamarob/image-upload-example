package de.jamarob.photowall.service;

import de.jamarob.photowall.model.Photo;
import de.jamarob.photowall.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    private final PhotoRepository repository;

    @Autowired
    public PhotoService(PhotoRepository repository) {
        this.repository = repository;
    }

    public List<Photo> findAll() {
        return repository.findAll();
    }

    public Photo savePhoto(Photo photoToSave) {
        return repository.save(photoToSave);
    }
}
