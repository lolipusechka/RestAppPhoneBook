package local.restphonebook.phonebook.service;

import local.restphonebook.phonebook.model.Phone;

import java.util.List;
import java.util.Map;

public interface PhoneService {

    void create(Long phoneNumber);

    List<Phone> readAll();

    Phone read(Integer id);

    boolean update(Phone phoneBook, Integer id, Long phoneNumber);

    boolean delete(Integer id);

}
