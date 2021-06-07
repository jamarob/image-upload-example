package de.jamarob.photowall.controller;

import de.jamarob.photowall.model.Photo;
import de.jamarob.photowall.repository.PhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PhotoControllerTest {

    @LocalServerPort
    private int port;

    private String url(){
        return "http://localhost:" + port + "/photo";
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PhotoRepository repository;

    @BeforeEach
    public void clearDatabase(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("Find all photos")
    public void findAllPhotos(){
        // Given
        repository.saveAll(List.of(
                Photo.builder().id("1").url("some-url").build(),
                Photo.builder().id("2").url("another-url").build(),
                Photo.builder().id("3").url("a-third-url").build()
        ));

        // When
        ResponseEntity<Photo[]> response = restTemplate.getForEntity(url(), Photo[].class);

        // Then
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), arrayContainingInAnyOrder(
                Photo.builder().id("1").url("some-url").build(),
                Photo.builder().id("2").url("another-url").build(),
                Photo.builder().id("3").url("a-third-url").build()
        ));
    }

}