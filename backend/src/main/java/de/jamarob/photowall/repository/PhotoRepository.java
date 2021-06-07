package de.jamarob.photowall.repository;

import de.jamarob.photowall.model.Photo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PhotoRepository extends PagingAndSortingRepository<Photo,String> {

    List<Photo> findAll();

}
