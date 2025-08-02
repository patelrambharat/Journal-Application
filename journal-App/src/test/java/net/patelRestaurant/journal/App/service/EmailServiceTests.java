package net.patelRestaurant.journal.App.service;

import net.patelRestaurant.journal.App.Service.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Disabled
    @Test
    void testSendMail() {
        emailService.sendEmail("patelrambharat@gmail.com",
                "Testing Java mail sender",
                "Hi, aap kaise hain ?");
    }
}
