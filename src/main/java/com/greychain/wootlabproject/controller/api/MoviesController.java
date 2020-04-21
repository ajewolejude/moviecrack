package com.greychain.wootlabproject.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greychain.wootlabproject.model.Movie;
import com.greychain.wootlabproject.payload.SingleRequest;
import com.greychain.wootlabproject.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    @Autowired
    private RestTemplate restTemplate;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAvailableOperations() throws JsonProcessingException {
        String serviceURL ="https://wootlab-moviedb.herokuapp.com/api/movie/list/all";
        String jsonString = restTemplate.getForObject(serviceURL, String.class);
        Movie[] movieList =new ObjectMapper().readValue(jsonString, Movie[].class);


        return new ResponseEntity<Movie[]>(movieList, HttpStatus.CREATED);
    }


    @PostMapping("/movies/view")
    public ResponseEntity<?> getMovieDetails(@Valid @RequestBody SingleRequest singleRequest) throws JsonProcessingException {
        String serviceURL ="https://wootlab-moviedb.herokuapp.com/api/movie/list/all";
        String jsonString = restTemplate.getForObject(serviceURL, String.class);
        Movie[] movieList =new ObjectMapper().readValue(jsonString, Movie[].class);
        Movie oneMovie = new Movie();
        for (Movie movie : movieList) {
            if (movie.getId().equals(singleRequest.getId())){
                oneMovie = movie;
                break;
            }
        }

        return new ResponseEntity<Movie>(oneMovie, HttpStatus.CREATED);

    }
}
