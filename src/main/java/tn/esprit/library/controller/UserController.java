package tn.esprit.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.library.entities.User;
import tn.esprit.library.services.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    IUserService userService;
    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }
    @GetMapping("/getall")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
    @PostMapping("/login/{email}/{pass}")
    public String login(@PathVariable String email, @PathVariable String pass) {
        User u = userService.checkProfile(email,pass);
        if (u==null)
            return "Verifier votre données";
        return generateToken(u.getId_user());
    }
    private String generateToken(Long userId) {
        long timestamp = System.currentTimeMillis();
        String key = "1a2fac207899e0a0e16d01c428feedcbc2b93513f8f8576d93ddf5411582c3df";  // Use a secure key in real scenarios

        return Jwts.builder().signWith(SignatureAlgorithm.HS256, key)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + 900000))
                .claim("userId", userId.toString())
                .compact();
    }
}
