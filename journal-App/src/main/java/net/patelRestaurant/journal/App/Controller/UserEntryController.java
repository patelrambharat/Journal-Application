package net.patelRestaurant.journal.App.Controller;

import net.patelRestaurant.journal.App.Service.UserEntryService;
import net.patelRestaurant.journal.App.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public List<User> getAllUser(){
        return userEntryService.getAll();
    }
    @PostMapping
    public void createUser(@RequestBody User user){
        userEntryService.saveNewUser(user);
    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String userName){
       User userInDb  =  userEntryService.findByUserName(userName);
       if(userInDb != null){
           userInDb.setUserName(user.getUserName());
           userInDb.setPassword(user.getPassword());
           userEntryService.saveEntry(userInDb);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
