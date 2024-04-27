package com.example.dayyy6.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.dayyy6.model.Model;
import com.example.dayyy6.repository.Repository;

@Service
public class Sservice
{
    @Autowired
    private Repository ab;
    //create user

    public Model createnewuser(@NonNull Model user)
    {
        return ab.save(user);
    }
    //getuser

    public List<Model> getAllusers()
    {
        return ab.findAll();
    }
    //getuserusingemail
    public Optional<Model>getModelByEmail(String email)
    {
        return ab.findByEmail(email);
    }
    //update user
    public Model updateUser(@NonNull String email,@RequestBody Model user)
    {
        return ab.findByEmail(email)
        .map(existingUser->{
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return ab.save(existingUser);
        }).orElseThrow(()->new RuntimeException("User not found with this email: "+email));

    }

    
    
    public void removeUser(@NonNull Integer userId)
    {
        ab.deleteById(userId);
    }

}
