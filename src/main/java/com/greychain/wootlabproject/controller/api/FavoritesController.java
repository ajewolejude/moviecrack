package com.greychain.wootlabproject.controller.api;
import com.greychain.wootlabproject.model.Favorites;
import com.greychain.wootlabproject.security.JwtTokenProvider;
import com.greychain.wootlabproject.services.FavoritesService;
import com.greychain.wootlabproject.services.MapValidationErrorService;
import com.greychain.wootlabproject.services.UserService;
import com.greychain.wootlabproject.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fav")
public class FavoritesController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){

        Favorites favorites = favoritesService.getOne(id);

        return new ResponseEntity<Favorites>(favorites, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> registerFav(@Valid @RequestBody Favorites favorites, BindingResult result){
        // Validate passwords match

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

        Favorites newFav = favoritesService.save(favorites);

        return  new ResponseEntity<Favorites>(newFav, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public Iterable<Favorites> getAllFav(){return favoritesService.getAll();}


    @GetMapping("/{id}/all")
    public ResponseEntity<?> getFav(@PathVariable Long id){
        List<Favorites> favorites = favoritesService.getUserFav(id);

        return new ResponseEntity< List<Favorites>>(favorites, HttpStatus.OK);
    }


    @GetMapping("/{user_id}/{movie_id}")
    public ResponseEntity<?> getMyFav(@PathVariable Long user_id, @PathVariable Long movie_id){

        Favorites  favorites1 = favoritesService.isMovieFav(user_id,movie_id);
        boolean result = false;
        if(favorites1 != null){
            result =  true;
        }
        return new ResponseEntity< Boolean>(result, HttpStatus.OK);
    }


    @GetMapping("/{user_id}/{movie_id}/delete")
    public ResponseEntity<?> deleteFav(@PathVariable Long user_id, @PathVariable Long movie_id){
        favoritesService.removeFav(user_id,movie_id);

        return new ResponseEntity<String>("Favorite was deleted", HttpStatus.OK);
    }
}
