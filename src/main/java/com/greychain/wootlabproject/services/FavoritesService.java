package com.greychain.wootlabproject.services;

import com.greychain.wootlabproject.model.Favorites;
import com.greychain.wootlabproject.repositories.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FavoritesService {

    @Autowired
    private FavoritesRepository favoritesRepository;

    public Favorites getOne(long id){
        return favoritesRepository.findById(id).get();
    }

    public List<Favorites> getAll(){
        return favoritesRepository.findAll();
    }

    public Favorites save(Favorites favorites){
        return favoritesRepository.save(favorites);
    }

    public void deleteById(Long id){
        favoritesRepository.deleteById(id);
    }

    public List<Favorites> getUserFav(Long id){
        return favoritesRepository.getUserFav(id);
    }

    public Favorites isMovieFav(Long user_id, Long movie_id){
        return favoritesRepository.isMovieFav(user_id,movie_id);
    }

    public void removeFav(Long user_id, Long movie_id){
        favoritesRepository.removeFav(user_id, movie_id);
    }


}
