package local.restphonebook.phonebook.controller;

import local.restphonebook.phonebook.model.Phone;
import local.restphonebook.phonebook.model.User;
import local.restphonebook.phonebook.service.PhoneService;
import local.restphonebook.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MainController {

    private final PhoneService phoneService;
    private final UserService userService;

    @Autowired
    public MainController(PhoneService ps, UserService us) {
        this.phoneService = ps;
        this.userService = us;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/users/{userId}/phones")
    public ResponseEntity<?> createPhone(@RequestBody Phone phone, @PathVariable(value = "userId") Integer userId) {
        if (phone == null || userService.read(userId) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.read(userId);
        phoneService.create(phone);
        phone.setUserId(userId);
        user.addPhoneToMap(phone);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> readUsers() {
        final List<User> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{userId}/phones")
    public ResponseEntity<List<Phone>> readPhones(@PathVariable(value = "userId") Integer userId) {
        final List<Phone> phones = phoneService.readAll().stream()
                .filter(phone -> phone.getUserId().equals(userId))
                .collect(Collectors.toList());

        return !phones.isEmpty()
                ? new ResponseEntity<>(phones, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{userId}/phones/{phoneId}")
    public ResponseEntity<Phone> readPhone(@PathVariable(value = "userId") Integer userId,
                                           @PathVariable(value = "phoneId") Integer phoneId) {
        final Phone phone = phoneService.read(phoneId);

        return phone != null && phone.getUserId().equals(userId)
                ? new ResponseEntity<>(phone, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> readUser(@PathVariable(name = "id") Integer id) {
        final User user = userService.read(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/name")
    public ResponseEntity<List<User>> readUserByName(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final String userName = user.getName();
        final List<User> users = userService.readByName(userName);

        return !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{userId}/phones/number")
    public ResponseEntity<List<Phone>> readPhoneByPhoneNumber(@PathVariable(value = "userId") Integer userId,
                                                 @RequestBody Phone phone) {
        if (phone == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final String phoneNumber = phone.getPhoneNumber();
        final List<Phone> phoneNumbers = phoneService.readByPhoneNumber(phoneNumber);
        List<Phone> filteredPhoneList = phoneNumbers.stream()
                .filter(phoneFromList -> phoneFromList.getUserId().equals(userId))
                .collect(Collectors.toList());

        return !filteredPhoneList.isEmpty()
                ? new ResponseEntity<>(filteredPhoneList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "userId") Integer userId, @RequestBody User user) {
        if (!userService.update(user, userId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            final User oldUser = userService.read(userId);
            final Map<Integer, Phone> phones = oldUser.getPhones();
            user.setPhones(phones);

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping(value = "/users/{userId}/phones/{phoneId}")
    public ResponseEntity<?> updatePhone(@PathVariable(name = "userId") Integer userId,
                                         @PathVariable(name = "phoneId") Integer phoneId, @RequestBody Phone phone) {
        if (phoneService.update(phone, phoneId) && userService.read(userId) != null) {
            final User user = userService.read(userId);

            phone.setUserId(userId);
            user.removePhoneFromMap(phoneId);
            user.addPhoneToMap(phone);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") Integer userId) {
        if (!userService.delete(1)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        /*final boolean deleted = userService.delete(userId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);*/
    }

    @DeleteMapping(value = "/users/{userId}/phones/{phoneId}")
    public ResponseEntity<?> deletePhone(@PathVariable(name = "userId") Integer userId,
                                         @PathVariable(name = "phoneId") Integer phoneId) {
        if (phoneService.delete(phoneId) && userService.read(userId) != null) {
            final User user = userService.read(userId);
            user.removePhoneFromMap(phoneId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        /*final User user = userService.read(userId);
        final boolean deleted = phoneService.delete(phoneId);
        user.removePhoneFromMap(phoneId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);*/
    }

}
