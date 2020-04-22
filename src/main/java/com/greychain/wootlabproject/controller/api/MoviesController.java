package com.greychain.wootlabproject.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greychain.wootlabproject.model.Favorites;
import com.greychain.wootlabproject.model.Movie;
import com.greychain.wootlabproject.payload.SingleRequest;
import com.greychain.wootlabproject.security.JwtTokenProvider;
import com.greychain.wootlabproject.services.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FavoritesService favoritesService;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAvailableOperations() throws JsonProcessingException {
        String serviceURL = "https://wootlab-moviedb.herokuapp.com/api/movie/list/all";
        String jsonString = restTemplate.getForObject(serviceURL, String.class);
        Movie[] movieList = new ObjectMapper().readValue(jsonString, Movie[].class);


        return new ResponseEntity<Movie[]>(movieList, HttpStatus.CREATED);
    }


    @PostMapping("/view")
    public ResponseEntity<?> getMovieDetails(@Valid @RequestBody SingleRequest singleRequest) throws JsonProcessingException {
        String serviceURL = "https://wootlab-moviedb.herokuapp.com/api/movie/list/all";
        String jsonString = restTemplate.getForObject(serviceURL, String.class);
        Movie[] movieList = new ObjectMapper().readValue(jsonString, Movie[].class);
        Movie oneMovie = new Movie();
        for (Movie movie : movieList) {
            if (movie.getId().equals(singleRequest.getId())) {
                oneMovie = movie;
                break;
            }
        }

        return new ResponseEntity<Movie>(oneMovie, HttpStatus.CREATED);

    }

    @PostMapping("/favorites")
    public ResponseEntity<?> getMyFavoriteMovies(@Valid @RequestBody SingleRequest singleRequest) throws JsonProcessingException {

        List<Favorites> favoritesList = favoritesService.getUserFav((long) singleRequest.getId());


        String serviceURL = "https://wootlab-moviedb.herokuapp.com/api/movie/list/all";
        String jsonString = restTemplate.getForObject(serviceURL, String.class);
        Movie[] movieList = new ObjectMapper().readValue(jsonString, Movie[].class);
        Movie[] myMovieList = new Movie[favoritesList.size()];
        for (Movie movie : movieList) {
            if (myMovieList[0] == null) {
                for (int i = 0; i < favoritesList.size(); i++) {

                    if (movie.getId().equals(favoritesList.get(i).getMovie_id().intValue())) {
                        myMovieList[i] = movie;
                    }
                }

            }

        }

        return new ResponseEntity<Movie[]>(myMovieList, HttpStatus.CREATED);

    }
}
