package de.jamarob.photowall.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import de.jamarob.photowall.model.Photo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary = new Cloudinary();

    public Photo uploadImage(File image) throws IOException {
        Map response = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
        String url = response.get("url").toString();
        String public_id = response.get("public_id").toString();
        return Photo.builder().id(public_id).url(url).build();
    }

    public void deletePhoto(String id) throws IOException {
        Map response = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        if(!response.get("result").toString().equals("ok")){
            throw new RuntimeException("Photo not found: "+id);
        }
    }

}
