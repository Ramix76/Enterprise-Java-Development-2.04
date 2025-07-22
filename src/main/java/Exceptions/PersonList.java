package Exceptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PersonList {
    private List<Person> people;
    private int idCounter = 1000;

    public PersonList() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Person p) {
        people.add(p);
    }

    public Person findByName(String name) {
        if (!Person.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name format");
        }

        return people.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Person not found"));
    }

    public Person clonePerson(Person original) {
        return new Person(++idCounter, original.getName(), original.getAge(), original.getOccupation());
    }

    public void writePersonToFile(Person person, String filePath) throws IOException {
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null.");
        }

        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(person.toString());
            writer.newLine();
        }
    }


    public List<Person> getPeople() {
        return people;
    }
}


