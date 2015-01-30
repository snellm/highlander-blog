package com.snell.michael.highlander.blog;

import java.util.Collection;

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
}
