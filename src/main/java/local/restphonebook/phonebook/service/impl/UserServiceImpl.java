package local.restphonebook.phonebook.service.impl;

import local.restphonebook.phonebook.model.User;
import local.restphonebook.phonebook.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Integer, User> userMap = new HashMap<>();
    private final AtomicInteger userIdHolder = new AtomicInteger();

    @Override
    public User create(User user) {
        final Integer userId = userIdHolder.incrementAndGet();
        user.setId(userId);
        userMap.put(userId, user);
        return user;
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
    public List<User> readByName(String name) {
        String lowerCaseName = name.toLowerCase(Locale.ROOT);
        return userMap.entrySet().stream()
                .map(integerUserEntry -> integerUserEntry.getValue())
                .filter(user -> (((user.getName().toLowerCase(Locale.ROOT).equals(lowerCaseName))
                        && (!lowerCaseName.equals("")))
                        || ((user.getName().toLowerCase(Locale.ROOT).startsWith(lowerCaseName))
                        && (!lowerCaseName.equals("")))))
                .collect(Collectors.toList());
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
