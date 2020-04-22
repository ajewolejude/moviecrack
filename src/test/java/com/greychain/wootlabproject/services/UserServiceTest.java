package com.greychain.wootlabproject.services;
import com.greychain.wootlabproject.model.Favorites;
import com.greychain.wootlabproject.model.User;
import com.greychain.wootlabproject.repositories.FavoritesRepository;
import com.greychain.wootlabproject.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @Mock
    private UserRepository userRepository;


    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TestEntityManager entityManager;

    @InjectMocks
    @Autowired
    private UserService userService;


    @Test
    public void should_find_all_users() {

        List<User>  datas = new ArrayList<>();
        datas.add(new User(1l,"jewolejude","Ajewole","Jude",bCryptPasswordEncoder.encode("jewolejude"),bCryptPasswordEncoder.encode("jewolejude")));
        datas.add(new User(1l,"jewolejude","Ajewole","Jude",bCryptPasswordEncoder.encode("jewolejude"),bCryptPasswordEncoder.encode("jewolejude")));
        datas.add(new User(1l,"jewolejude","Ajewole","Jude",bCryptPasswordEncoder.encode("jewolejude"),bCryptPasswordEncoder.encode("jewolejude")));

        given(userRepository.findAll()).willReturn(datas);


        List<User> expected = userService.getAllUsers();
        assertEquals(expected,datas);
    }





    @Test
    public void getUserByUsername() {

        final String username = "jewolejude" ;

        final User user = new User(1l,"jewolejude","Ajewole","Jude",bCryptPasswordEncoder.encode("jewolejude"),bCryptPasswordEncoder.encode("jewolejude"));

        given(userService.getUserByUsername(username)).willReturn(user);
        final User expected = userService.getUserByUsername(username);

        assertThat(expected).isNotNull();

    }



}