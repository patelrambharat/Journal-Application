package net.patelRestaurant.journal.App.Service;

import lombok.extern.slf4j.Slf4j;
import net.patelRestaurant.journal.App.Repository.UserEntryRepo;
import net.patelRestaurant.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserEntryService {
    @Autowired
    UserEntryRepo userEntryRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger  = LoggerFactory.getLogger(UserEntryService.class);
    public void saveEntry(User user){
        userEntryRepo.save(user);
    }

    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userEntryRepo.save(user);
//            log.error("Error occurred for {}",user.getUserName());
//            logger.warn("hahahahahaha");
//            logger.info("hahahahahaha");
//            logger.debug("hahahahahaha");
//            logger.trace("hahahahahaha");
            return true;
        }
        catch (Exception e){

            return false;
        }

    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userEntryRepo.save(user);
    }
    public List<User> getAll(){
        return userEntryRepo.findAll();
    }
    public Optional<User> findById(ObjectId id){
        return userEntryRepo.findById(id);
    }
    public void deleteById(ObjectId id){
        userEntryRepo.deleteById(id);
    }
    public User findByUserName(String username){
        return userEntryRepo.findByUserName(username);
    }
}
