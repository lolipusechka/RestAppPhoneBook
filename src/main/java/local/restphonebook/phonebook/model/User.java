package local.restphonebook.phonebook.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class User {

    private Integer id;
    private String name;
    private Map<Integer, Phone> phones = new HashMap<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPhoneToMap(Phone phone) {
        phones.put(phone.getId(), phone);
    }

    public void removePhoneFromMap(Integer phoneId) {
        phones.remove(phoneId);
    }

    public void setPhones(Map<Integer, Phone> phones) {
        this.phones = phones;
    }

    public Map<Integer, Phone> getPhones() {
        return phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(phones, user.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phones);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones=" + phones +
                '}';
    }
}
