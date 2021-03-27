package local.restphonebook.phonebook.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

}
