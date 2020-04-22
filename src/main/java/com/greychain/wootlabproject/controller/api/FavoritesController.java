package com.greychain.wootlabproject.controller.api;
import com.greychain.wootlabproject.model.Favorites;
import com.greychain.wootlabproject.security.JwtTokenProvider;
import com.greychain.wootlabproject.services.FavoritesService;
import com.greychain.wootlabproject.services.MapValidationErrorService;
import com.greychain.wootlabproject.services.UserService;
import com.greychain.wootlabproject.validator.UserValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value="Favorite Module", description="Operations pertaining to favorites")
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



    @ApiOperation(value = "Get a favorite", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){

        Favorites favorites = favoritesService.getOne(id);

        return new ResponseEntity<Favorites>(favorites, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new favorite", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @PostMapping("/add")
    public ResponseEntity<?> registerFav(@Valid @RequestBody Favorites favorites, BindingResult result){
        // Validate passwords match

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

        Favorites newFav = favoritesService.save(favorites);

        return  new ResponseEntity<Favorites>(newFav, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Get All Favorite", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/all")
    public Iterable<Favorites> getAllFav(){return favoritesService.getAll();}


    @ApiOperation(value = "Get list of a user's favorites", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/{id}/all")
    public ResponseEntity<?> getFav(@PathVariable Long id){
        List<Favorites> favorites = favoritesService.getUserFav(id);

        return new ResponseEntity< List<Favorites>>(favorites, HttpStatus.OK);
    }


    @ApiOperation(value = "Determine if a movie is a user's favorite", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/{user_id}/{movie_id}")
    public ResponseEntity<?> getMyFav(@PathVariable Long user_id, @PathVariable Long movie_id){

        Favorites  favorites1 = favoritesService.isMovieFav(user_id,movie_id);
        boolean result = false;
        if(favorites1 != null){
            result =  true;
        }
        return new ResponseEntity< Boolean>(result, HttpStatus.OK);
    }


    @ApiOperation(value = "Remove movie from favorite", response = ResponseEntity.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/{user_id}/{movie_id}/delete")
    public ResponseEntity<?> deleteFav(@PathVariable Long user_id, @PathVariable Long movie_id){
        favoritesService.removeFav(user_id,movie_id);

        return new ResponseEntity<String>("Favorite was deleted", HttpStatus.OK);
    }
}
