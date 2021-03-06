package com.snell.michael.highlander.blog;

import com.google.common.base.Predicate;

import java.util.Collection;

import static com.google.common.collect.Collections2.filter;
import static com.snell.michael.highlander.Highlander.only;

public class Find {
    public Person findNaive(String email, Collection<Person> people) {
        for (Person person : people) {
            if (email.equalsIgnoreCase(person.getEmail())) {
                return person;
            }
        }
        return null;
    }

    public Person findVerbose(String email, Collection<Person> people) {
        Person found = null;
        for (Person person : people) {
            if (email.equalsIgnoreCase(person.getEmail())) {
                if (found == null) {
                    found = person;
                } else {
                    throw new RuntimeException("Found multiple people with email " + email);
                }
            }
        }
        if (found == null) {
            throw new RuntimeException("Found no people with email " + email);
        } else {
            return found;
        }
    }

    public Person findStream(String email, Collection<Person> people) {
        return people.stream()
            .filter(person -> email.equalsIgnoreCase(person.getEmail()))
            .findFirst().get();
    }

    public Person findOnlyGuava(String email, Collection<Person> people) {
        return only(filter(people, new Predicate<Person>() {
            public boolean apply(Person person) {
                return email.equalsIgnoreCase(person.getEmail());
            }
        }));
    }

    public Person findOnlyStream(String email, Collection<Person> people) {
        return only(people, person -> email.equalsIgnoreCase(person.getEmail()));
    }
}
