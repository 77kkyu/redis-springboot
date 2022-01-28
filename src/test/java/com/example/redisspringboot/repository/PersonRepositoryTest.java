package com.example.redisspringboot.repository;

import com.example.redisspringboot.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void 전체삭제() {
        personRepository.deleteAll();
    }

    @Test
    public void 레디스Crud() {
        Person person = new Person();
        person.setId(null);
        person.setName("77kkyu");
        person.setEmail("77kkyu@gmail.com");

        Person save = personRepository.save(person);

        Optional<Person> findPerson = personRepository.findById(save.getId());
        assertThat(findPerson.isPresent()).isEqualTo(Boolean.TRUE);
        assertThat(findPerson.get().getName()).isEqualTo(person.getName());
    }

}
