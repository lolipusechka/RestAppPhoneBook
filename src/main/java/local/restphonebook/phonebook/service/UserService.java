package local.restphonebook.phonebook.service;

import local.restphonebook.phonebook.model.User;

import java.util.List;

public interface UserService {

    void create(User user);

    List<User> readAll();

    User read(Integer id);

    boolean update(User user, Integer id);

    boolean delete(Integer id);

}
