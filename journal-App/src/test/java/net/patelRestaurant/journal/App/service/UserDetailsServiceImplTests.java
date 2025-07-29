package net.patelRestaurant.journal.App.service;

import net.patelRestaurant.journal.App.Repository.UserEntryRepo;
import net.patelRestaurant.journal.App.Service.UserDetailsImpl;
import net.patelRestaurant.journal.App.Service.UserEntryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;



public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsImpl userDetailsService;

    @Mock
    private UserEntryRepo userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    void loadUserByUsernameTest() {
        // Mocking a Spring Security UserDetails object with username, password, and authorities
        UserDetails mockUser = User.builder()
                .username("ram") //
                .password("inrinrick")
                .authorities(Collections.emptyList())
                .build();

        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn((net.patelRestaurant.journal.App.entity.User) mockUser);

        UserDetails user = userDetailsService.loadUserByUsername("ram");

        Assertions.assertNotNull(user);
    }

}