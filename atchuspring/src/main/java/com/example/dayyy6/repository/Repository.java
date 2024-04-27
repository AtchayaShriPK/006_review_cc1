package com.example.dayyy6.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dayyy6.model.Model;

public interface Repository extends JpaRepository<Model,Integer>
{
    Optional<Model> findByEmail(String username);
}
