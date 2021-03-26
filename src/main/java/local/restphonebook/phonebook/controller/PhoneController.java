package local.restphonebook.phonebook.controller;

import local.restphonebook.phonebook.model.Phone;
import local.restphonebook.phonebook.model.User;
import local.restphonebook.phonebook.service.PhoneService;
import local.restphonebook.phonebook.service.UserService;
import local.restphonebook.phonebook.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneController {

    private final PhoneService phoneService;
    private final UserService userService = new UserServiceImpl();

    @Autowired
    public PhoneController(PhoneService phoneService, UserController userController) {
        this.phoneService = phoneService;
    }

    @PostMapping(value = "/users/{userId}/phones")
    public ResponseEntity<?> create(@PathVariable(value = "userId") Integer userId, @RequestBody Phone phone) {
        User user = userService.read(userId);
        phoneService.create(phone);
        phone.setUserId(userId);
        user.addPhoneToList(phone);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/phones")
    public ResponseEntity<List<Phone>> read() {
        final List<Phone> phones = phoneService.readAll();

        return phones != null && !phones.isEmpty()
                ? new ResponseEntity<>(phones, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
