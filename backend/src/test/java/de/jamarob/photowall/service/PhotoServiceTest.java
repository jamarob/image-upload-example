package de.jamarob.photowall.service;

import de.jamarob.photowall.model.Photo;
import de.jamarob.photowall.repository.PhotoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PhotoServiceTest {

    private PhotoRepository repository = mock(PhotoRepository.class);
    private PhotoService service = new PhotoService(repository);

    @Test
    @DisplayName("Find all photos")
    public void findAllPhotos(){
        // Given
        when(repository.findAll()).thenReturn(List.of(
                Photo.builder().id("1").url("some-url").build(),
                Photo.builder().id("2").url("another-url").build(),
                Photo.builder().id("3").url("a-third-url").build()
        ));

        // When
        List<Photo> actualPhotos = service.findAll();

        // Then
        List<Photo> expectedPhotos = List.of(
                Photo.builder().id("1").url("some-url").build(),
                Photo.builder().id("2").url("another-url").build(),
                Photo.builder().id("3").url("a-third-url").build()
        );
        assertThat(actualPhotos, is(expectedPhotos));
    }

}