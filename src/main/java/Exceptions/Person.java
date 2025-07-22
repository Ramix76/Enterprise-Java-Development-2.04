package Exceptions;

public class Person {
    private int id;
    private int age;
    private String name;
    private String occupation;

    public Person(int id, String name, int age, String occupation) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Name must be formatted as 'FirstName LastName'");
        }
        this.id = id;
        this.name = name;
        setAge(age);
        this.occupation = occupation;
    }

    public static boolean isValidName(String name) {
        return name != null && name.matches("^[A-Z][a-zA-Z]*\\s[A-Z][a-zA-Z]*$");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Name must be formatted as 'FirstName LastName'");
        }
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same object
        if (obj == null || getClass() != obj.getClass()) return false;

        Person other = (Person) obj;

        return name.equals(other.name) &&
                age == other.age &&
                occupation.equals(other.occupation);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + age;
        result = 31 * result + name.hashCode();
        result = 31 * result + occupation.hashCode();
        return result;
    }
}