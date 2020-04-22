package com.greychain.wootlabproject.services;

import com.greychain.wootlabproject.model.Favorites;
import com.greychain.wootlabproject.repositories.FavoritesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FavoritesServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Mock
    private FavoritesRepository favoritesRepository;

    @InjectMocks
    @Autowired
    private FavoritesService favoritesService;


    @Test
    public void should_find_all_favorites() {

        List<Favorites>  datas = new ArrayList<>();
        datas.add(new Favorites(1l,1l,1l));
        datas.add(new Favorites(2l,1l,111l));
        datas.add(new Favorites(3l,1l,59l));

        given(favoritesRepository.findAll()).willReturn(datas);


        List<Favorites> expected = favoritesService.getAll();
        assertEquals(expected,datas);
    }


    @Test
    public void should_save_favorites() {

        final Favorites favorites = new Favorites(1l,2l,3l);

        given(favoritesRepository.save(favorites)).willAnswer(invocation -> invocation.getArgument(0));

        Favorites savedFav = favoritesService.save(favorites);
        assertThat(savedFav).isNotNull();


        // verify if the save method is called when createTodo is called too
        verify(favoritesRepository, times(1)).save(any(Favorites.class));

    }

    @Test
    public void should_delete_favorites_by_id() {

        final Long favId = 1l;

        favoritesService.deleteById(favId);
        favoritesService.deleteById(favId);


        // verify if the save method is called when createTodo is called too
        verify(favoritesRepository, times(2)).deleteById(favId);

    }

    @Test
    public void should_remove_a_user_favorite() {

        final Long user_id = 1l;
        final Long movie_id = 3l;

        favoritesService.removeFav(user_id, movie_id);
        favoritesService.removeFav(user_id,movie_id);


        // verify if the save method is called when createTodo is called too
        verify(favoritesRepository, times(2)).removeFav(user_id, movie_id);

    }

    @Test
    public void should_get_fav() {

        final Long id = 1l;

        final Favorites favorites = new Favorites(1l, 2l,3l);

        given(favoritesRepository.findById(id)).willReturn(Optional.of(favorites));
        final Favorites expected = favoritesService.getOne(id);

       assertThat(expected).isNotNull();

    }

    @Test
    public void should_get_user_favs() {

        final Long id = 1l;

        List<Favorites>  datas = new ArrayList<>();
        datas.add(new Favorites(1l,1l,1l));
        datas.add(new Favorites(1l,1l,111l));
        datas.add(new Favorites(3l,1l,59l));

        given(favoritesRepository.getUserFav(id)).willReturn(datas);

        final List<Favorites> expected = favoritesService.getUserFav(id);

        assertThat(expected).isNotNull();

    }



    @Test
    void getUserFav() {
    }

    @Test
    void isMovieFav() {

        final Long user_id = 1l;
        final Long movie_id = 3l;


        final Favorites favorites = new Favorites(1l, 3l,1l);

        given(favoritesRepository.isMovieFav(user_id,movie_id)).willReturn(favorites);

        final Favorites expected = favoritesService.isMovieFav(user_id,movie_id);



        assertEquals(expected,favorites);
    }

}