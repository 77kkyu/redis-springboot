package com.example.redisspringboot.service;

import com.example.redisspringboot.repository.PersonRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

}
