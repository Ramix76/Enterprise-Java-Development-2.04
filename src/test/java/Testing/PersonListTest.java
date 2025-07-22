package Testing;

import Exceptions.Person;
import Exceptions.PersonList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class PersonListTest {

    private PersonList personsList;
    private Person person;

    @BeforeEach
    public void setup() {
        personsList = new PersonList();
        person = new Person(1, "John Smith", 30, "Teacher");
        personsList.addPerson(person);
    }

    @Test
    public void testSetAgeNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            person.setAge(-5);
        });
    }

    @Test
    public void testFindByNameReturnsCorrectPerson() {
        Person found = personsList.findByName("John Smith");
        assertEquals(person, found);
    }

    @Test
    public void testFindByNameWithInvalidFormatThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            personsList.findByName("johnsmith");
        });
    }

    @Test
    public void testClonePersonCreatesNewWithSameData() {
        Person clone = personsList.clonePerson(person);
        assertNotEquals(clone.getId(), person.getId());
        assertTrue(clone.equals(person));
    }
}

