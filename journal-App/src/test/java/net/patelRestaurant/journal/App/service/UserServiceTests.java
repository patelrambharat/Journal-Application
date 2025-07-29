package net.patelRestaurant.journal.App.service;

import net.patelRestaurant.journal.App.Repository.UserEntryRepo;
import net.patelRestaurant.journal.App.Service.UserEntryService;
import net.patelRestaurant.journal.App.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("dev")
public class UserServiceTests {
    @Autowired
    private UserEntryRepo userEntryRepo;

    @Autowired
    private UserEntryService userEntryService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userEntryService.saveNewUser(user));
    }


    @ParameterizedTest
        @CsvSource({
                "1, 1, 2",
                "2, 10, 12",
                "3, 3, 9"
        }) public void test(int a, int b, int expected){
            assertEquals(expected, a+b);
        }
    }

