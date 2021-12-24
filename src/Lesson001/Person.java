package Lesson001;

public class Person {
    private String fullName;
    private int age;

    public static void main(String[] args) {
        Person noName = new Person();
        Person kek = new Person("Kek", 12);
        noName.talk();
        kek.talk();
    }

    public Person(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public Person() {
    }

    public void talk() {
        if (this.fullName != null) {
            System.out.println("Привет, я "+fullName+" и мне "+this.age+" лет.");
        }
    }
}
