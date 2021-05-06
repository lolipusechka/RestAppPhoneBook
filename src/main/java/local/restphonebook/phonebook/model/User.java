package local.restphonebook.phonebook.model;

import java.util.*;


public class User {

    private Integer id;
    private String name;
    private List<Phone> phones = new ArrayList<>();

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
        phones.add(phone);
    }

    public void removePhoneFromMap(Integer phoneId) {
        int i;
        for (i = 0; i < phones.size(); i++) {
            if (phones.get(i).getId().equals(phoneId)) {
                break;
            }
        }
        phones.remove(i);
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Phone> getPhones() {
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
