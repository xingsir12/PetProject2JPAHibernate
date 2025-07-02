package ru.xing.springcourse.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.xing.springcourse.dao.PersonDAO;
import ru.xing.springcourse.models.Person;
import ru.xing.springcourse.services.PeopleService;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.getPersonByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("full_name", "", "Человек с таким ФИО уже существует");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return peopleService.getClass().equals(clazz);
    }
}
