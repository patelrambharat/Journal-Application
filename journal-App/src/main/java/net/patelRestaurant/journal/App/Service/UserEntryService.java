package net.patelRestaurant.journal.App.Service;

import net.patelRestaurant.journal.App.Repository.UserEntryRepo;
import net.patelRestaurant.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserEntryService {
    @Autowired
    UserEntryRepo userEntryRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User user){
        userEntryRepo.save(user);
    }

    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userEntryRepo.save(user);
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
