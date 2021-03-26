package local.restphonebook.phonebook.service;

import local.restphonebook.phonebook.model.User;

import java.util.List;

public interface UserService {

    void create(String name);

    List<User> readAll();

    User read(Integer id);

    boolean update(User user, Integer id, String name);

    boolean delete(Integer id);

}
