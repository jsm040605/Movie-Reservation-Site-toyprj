package com.example.theater_proj.controller;

import com.example.theater_proj.entity.User;
import com.example.theater_proj.repository.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/create")
    public String createUser(@RequestBody User user){

        userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return location.toString();
    }

    @GetMapping(path = "/create/{id}") // todo : view 추가 -> redirect
    public ResponseEntity<EntityModel<User>> retrieveUserById(@PathVariable Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new Exception("id : " + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());

        return ResponseEntity.ok(entityModel);
    }
}
