package com.greychain.wootlabproject.repositories;

import com.greychain.wootlabproject.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    @Query(value = "select * from favorites f where f.user_id= :user_id",
            nativeQuery=true)
    List<Favorites> getUserFav(@Param("user_id") Long user_id);


    @Query(value = "select * from favorites f where f.user_id= :user_id and f.movie_id= :movie_id",
          nativeQuery=true)
    Favorites isMovieFav(@Param("user_id") Long user_id, @Param("movie_id") Long movie_id);



    @Modifying
    @Query(value = "delete from favorites f where f.user_id= :user_id and f.movie_id= :movie_id",
            nativeQuery=true)
    void removeFav(@Param("user_id") Long user_id, @Param("movie_id") Long movie_id);
}
