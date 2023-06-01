package com.volkov.restapiapp.services;

import com.volkov.restapiapp.models.Person;
import com.volkov.restapiapp.repositories.PeopleRepository;
import com.volkov.restapiapp.util.PersonNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PeopleRepository peopleRepository;

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        var person = peopleRepository.findById(id);
        return person.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void save(Person person) {
        addServiceInfo(person);
        peopleRepository.save(person);
    }

    private void addServiceInfo(Person person) {
        person.setCreatedAt(new Date());
        person.setUpdatedAt(new Date());
        person.setCreatedWho("ADMIN");
    }
}
