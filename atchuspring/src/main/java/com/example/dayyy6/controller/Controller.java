package com.example.dayyy6.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dayyy6.model.Model;
import com.example.dayyy6.service.Sservice;


@RestController
@RequestMapping("/api/users")
public class Controller
{
    

@Autowired
private Sservice userService;

@PostMapping("/createUser")

public ResponseEntity<Model> createUser(@NonNull @RequestBody Model user) {

Model createdUser = userService.createnewuser(user);

return new ResponseEntity<>(createdUser, HttpStatus.OK);

}

@GetMapping("/readUser/{email}")

//@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")

public ResponseEntity<Model> getUserByEmail(@PathVariable String email)
{

Optional<Model> user = userService.getModelByEmail(email);

return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))

.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

}

@GetMapping("/readUsers")

// @PreAuthorize("hasAuthority('ADMIN')")

public ResponseEntity<List<Model>> getAllUsers()
{

List<Model> users = userService.getAllusers();

return new ResponseEntity<>(users, HttpStatus.OK);

}

@PutMapping("/updateUser/{email}")

// @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")

public ResponseEntity<Model> updateUser(@NonNull @PathVariable String email,@RequestBody Model updateRequest)
{

Model updated = userService.updateUser(email, updateRequest);

return new ResponseEntity<>(updated, HttpStatus.OK);

}

@DeleteMapping("/deleteUser/{userId}")

// @PreAuthorize("hasAuthority('ADMIN')")

public ResponseEntity<Void> removeUser(@NonNull @PathVariable Integer userId) {

userService.removeUser(userId);

return new ResponseEntity<>(HttpStatus.NO_CONTENT);

}

}
