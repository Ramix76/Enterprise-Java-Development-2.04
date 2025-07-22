package Exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        PersonList personList = new PersonList();
        List<Person> validPersons = new ArrayList<>();

        // Define test cases (some valid, some invalid)
        Object[][] testData = {
                {1, "John Smith", 30, "Teacher"},         // ✅ valid
                {2, "Jane Doe", 25, "Engineer"},          // ✅ valid
                {3, "invalidname", 40, "Doctor"},         // ❌ invalid name
                {4, "Alice Johnson", -5, "Nurse"},        // ❌ invalid age
                {5, "Bob Marley", 50, "Musician"},        // ✅ valid
                {6, "Tom", 20, "Developer"},              // ❌ invalid name
                {7, "Mary Lee", 0, "Artist"}              // ✅ valid
        };

        System.out.println("🚀 Creating Person objects...\n");

        for (Object[] data : testData) {
            try {
                int id = (int) data[0];
                String name = (String) data[1];
                int age = (int) data[2];
                String occupation = (String) data[3];

                Person person = new Person(id, name, age, occupation);
                personList.addPerson(person);
                validPersons.add(person);
                System.out.println("✅ Created and added: " + person);

            } catch (IllegalArgumentException e) {
                System.err.println("❌ Error creating person (" + data[1] + "): " + e.getMessage());
            }
        }

        System.out.println("\n🧪 Testing findByName...\n");

        // ✅ Valid format but not in list
        try {
            Person missing = personList.findByName("Elon Musk");
            System.out.println("Found person: " + missing);
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Invalid name format: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("❌ Person not found: " + e.getMessage());
        }

        // ❌ Invalid name format
        try {
            personList.findByName("johnsmith");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Invalid name format: " + e.getMessage());
        }

        // ✅ Valid format and found
        try {
            Person found = personList.findByName("Jane Doe");
            System.out.println("✅ Found person: " + found);
        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage());
        }

        System.out.println("\n📝 Saving valid persons to file...\n");

        for (Person p : validPersons) {
            personList.writePersonToFile(p, "persons.txt");
        }

        System.out.println("✅ All valid persons saved to 'persons.txt'.");
    }
}
