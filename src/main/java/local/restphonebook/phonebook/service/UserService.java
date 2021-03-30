package local.restphonebook.phonebook.service;

import local.restphonebook.phonebook.model.User;

import java.util.List;

public interface UserService {

    User create(User user);

    List<User> readAll();

    User read(Integer id);

    List<User> readByName(String name);

    boolean update(User user, Integer id);

    boolean delete(Integer id);

}
