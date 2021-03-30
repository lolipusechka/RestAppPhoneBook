package local.restphonebook.phonebook.service;

import local.restphonebook.phonebook.model.Phone;

import java.util.List;

public interface PhoneService {

    void create(Phone phone);

    List<Phone> readAll();

    Phone read(Integer id);

    List<Phone> readByPhoneNumber(String phoneNumber);

    boolean update(Phone phone, Integer id);

    boolean delete(Integer id);

}
