import java.util.HashSet;

public class Patient {
    private String name;
    private int age;
    private HashSet<String> dieses = new HashSet<String>();
    private String email;
    private int slot;

    public Patient() {
    }

    public Patient(String name, int age, HashSet dieses, String email, int slot) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.dieses = dieses;
        this.slot = slot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return this.slot;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDieses(HashSet al) {
        this.dieses = al;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public int getAge() {
        return this.age;
    }

    public HashSet getDieses() {
        return this.dieses;
    }
}