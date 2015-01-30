package com.snell.michael.highlander.blog;

import com.google.common.base.Predicate;

import java.util.Collection;

import static com.google.common.collect.Collections2.filter;

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
            .filter(p -> email.equalsIgnoreCase(p.getEmail()))
            .findFirst().get();
    }

    public Person findOnlyGuava(String email, Collection<Person> people) {
        return only(filter(people, hasEmail(email)));
    }

    public Person findOnlyStream(String email, Collection<Person> people) {
        return only(people.stream().filter(p -> email.equalsIgnoreCase(p.getEmail())));
    }

    private Predicate<Person> hasEmail(String email) {
        return new Predicate<Person>() {
            @Override
            public boolean apply(Person person) {
                return email.equalsIgnoreCase(person.getEmail());
            }
        };
    }
}
