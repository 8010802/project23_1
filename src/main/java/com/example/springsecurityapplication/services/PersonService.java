package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //////////

    public Person getPersonFindByLogin(Person person){
        Optional<Person> person_db = personRepository.findByLogin(person.getLogin());
        return person_db.orElse(null);
    }

    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }

    public Object getAllPersons() {
        return null;
    }

    //получение пользователя по id
    public Person getPersonId(int id){
        Optional<Person> optionalOrder = personRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    @Transactional
    public void updateRole1(Person person) {
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }

    @Transactional
    public void updateRole2(Person person) {
        person.setRole("ROLE_ADMIN");
        personRepository.save(person);
    }

    @Transactional
    public void updateRole3(Person person) {
        person.setRole("ROLE_SELLER");
        personRepository.save(person);
    }

    @Transactional
    public void updateRole4(Person person) {
        person.setRole("ROLE_BLOCK");
        personRepository.save(person);
    }

    // удаление пользователя по id
    @Transactional
    public void deleteUser(int id){
        personRepository.deleteById(id);
    }


}
