package local.restphonebook.phonebook.service;

import local.restphonebook.phonebook.model.Phone;
import local.restphonebook.phonebook.model.User;

import java.util.List;

public interface PhoneService {

    void create(Phone phone);

    List<Phone> readAll();

    Phone read(Integer id);

    boolean update(Phone phone, Integer id);

    boolean delete(Integer id);

}
