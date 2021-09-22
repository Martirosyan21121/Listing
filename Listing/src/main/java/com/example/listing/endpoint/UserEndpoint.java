package com.example.listing.endpoint;

import com.example.listing.model.User;
import com.example.listing.repasitory.UserRepasitory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserEndpoint {

    @Autowired
    UserRepasitory userRepasitory;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> userResponseEntity(@PathVariable("id") int id) {
        Optional<User> user = userRepasitory.findById(id);
        if (user.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }

    @PostMapping("/user")
    public User companies(@RequestBody User company) {
        return userRepasitory.save(company);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> userResponseEntity(@PathVariable("id") int id, @RequestBody User user) {
        Optional<User> user1 = userRepasitory.findById(id);
        if (user1.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        User user2 = user1.get();
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setRole(user.getRole());
        user2.setSurname(user.getSurname());
        user2.setCategoryId(user.getCategoryId());


        return ResponseEntity.ok().body(userRepasitory.save(user));

    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deletebyid(@PathVariable("id") int id) {
        Optional<User> user = userRepasitory.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        userRepasitory.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/user")
    public List<User> companies() {
        return userRepasitory.findAll();
    }
}
