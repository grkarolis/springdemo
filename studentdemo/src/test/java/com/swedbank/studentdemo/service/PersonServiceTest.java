package com.swedbank.studentdemo.service;

import com.swedbank.studentdemo.model.domain.Person;
import com.swedbank.studentdemo.repository.PersonRepository;
import com.swedbank.studentdemo.util.BarFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repositoryMock;

    @Mock
    private BarFormatter barFormatterMock;

    @InjectMocks
    private PersonService service;

    @Test
    public void When_FirstNamePresent_Expect_PersonsMatchingFirstName() {
        Person expected = Person.builder()
                .firstName("name").build();

        when(repositoryMock.findAllByFirstName(anyString())).thenReturn(List.of(expected));

        List<Person> actual = service.fetchPersons("name", null);

        assertEquals(expected.getFirstName(), actual.get(0).getFirstName());
    }

    @Test
    public void When_LastNamePresent_Expect_PersonsMatchingLastName() {
        Person expected = Person.builder()
                .lastName("lastName").build();

        when(repositoryMock.findAllByLastName(anyString())).thenReturn(List.of(expected));

        List<Person> actual = service.fetchPersons(null, "lastName");

        assertEquals(expected.getLastName(), actual.get(0).getLastName());
    }
}
