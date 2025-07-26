package net.patelRestaurant.journal.App.Service;

import net.patelRestaurant.journal.App.Repository.UserEntryRepo;
import net.patelRestaurant.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntryService {
    @Autowired
    UserEntryRepo userEntryRepo;

    public void saveEntry(User user){
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
