package net.patelRestaurant.journal.App.Controller;

import lombok.extern.slf4j.Slf4j;
import net.patelRestaurant.journal.App.Service.UserDetailsImpl;
import net.patelRestaurant.journal.App.Service.UserEntryService;
import net.patelRestaurant.journal.App.entity.User;
import net.patelRestaurant.journal.App.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@Slf4j

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetails;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    UserEntryService userEntryService;
    @GetMapping("health-check")
    public String healthCheck(){
        return "ok";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        userEntryService.saveNewUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetail = userDetails.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetail.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
