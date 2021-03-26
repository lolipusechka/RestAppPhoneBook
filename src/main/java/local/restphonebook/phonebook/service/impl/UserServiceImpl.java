package local.restphonebook.phonebook.service.impl;

import local.restphonebook.phonebook.model.User;
import local.restphonebook.phonebook.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Integer, User> userMap = new HashMap<>();
    private final AtomicInteger userIdHolder = new AtomicInteger();

    @Override
    public void create(User user) {
        final Integer userId = userIdHolder.incrementAndGet();
        user.setId(userId);
        userMap.put(userId, user);
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User read(Integer id) {
        return userMap.get(id);
    }

    @Override
    public boolean update(User user, Integer id) {
        if (userMap.containsKey(id)) {
            user.setId(id);
            userMap.put(id, user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return userMap.remove(id) != null;
    }
}
