package local.restphonebook.phonebook.controller;

import local.restphonebook.phonebook.model.Phone;
import local.restphonebook.phonebook.model.User;
import local.restphonebook.phonebook.service.PhoneService;
import local.restphonebook.phonebook.service.UserService;
import local.restphonebook.phonebook.service.impl.PhoneServiceImpl;
import local.restphonebook.phonebook.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {

    @Mock
    private final PhoneService phoneService = new PhoneServiceImpl();
    @Mock
    private final UserService userService = new UserServiceImpl();

    @InjectMocks
    private final MainController mainController = new MainController(phoneService, userService);

    @BeforeEach
    public void Setup() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }


    @Test
    void createUser_whenDataIsValid_thenReturnHttpCREATED() {
        ResponseEntity<?> responseEntity = mainController.createUser(new User());

        User user = new User();
        user.setId(1);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(user, userService.read(1));
    }

    @Test
    void createUser_whenDataIsNotValid_thenReturnHttpBAD_REQUEST() {
        assertEquals(HttpStatus.BAD_REQUEST, mainController.createUser(null).getStatusCode());
    }

    @Test
    void createPhone_whenDataIsValid_thenReturnHttpCREATED() {
        mainController.createUser(new User());
        ResponseEntity<?> responseEntityPhone = mainController.createPhone(new Phone(), 1);

        Phone phone = new Phone();
        phone.setId(1);
        phone.setUserId(1);

        assertEquals(HttpStatus.CREATED, responseEntityPhone.getStatusCode());
        assertEquals(phone, phoneService.read(1));
    }

    @Test
    void createPhone_whenDataIsNotValid_thenReturnHttpBAD_REQUEST() {
        assertEquals(HttpStatus.BAD_REQUEST, mainController.createPhone(new Phone(), 1).getStatusCode());
    }

    @Test
    void createPhone_whenPhoneIsNull_thenReturnHttpBAD_REQUEST() {
        mainController.createUser(new User());
        assertEquals(HttpStatus.BAD_REQUEST, mainController.createPhone(null, 1).getStatusCode());
    }

    @Test
    void readUsers_whenMapHaveValues_thenReturnHttpOK() {
        mainController.createUser(new User());

        assertEquals(HttpStatus.OK, mainController.readUsers().getStatusCode());
    }

    @Test
    void readUsers_whenMapNotHaveValues_thenReturnHttpNOT_FOUND() {
        assertEquals(HttpStatus.NOT_FOUND, mainController.readUsers().getStatusCode());
    }

    @Test
    void readPhones_whenMapHaveValues_thenReturnHttpOK() {
        mainController.createUser(new User());
        mainController.createPhone(new Phone(), 1);

        assertEquals(HttpStatus.OK, mainController.readPhones(1).getStatusCode());
    }

    @Test
    void readPhones_whenMapNotHaveValues_thenReturnHttpNOT_FOUND() {
        assertEquals(HttpStatus.NOT_FOUND, mainController.readPhones(1).getStatusCode());
    }

    @Test
    void readPhone_whenDataIsValid_thenReturnHttpOK() {
        mainController.createUser(new User());
        mainController.createPhone(new Phone(), 1);

        assertEquals(HttpStatus.OK, mainController.readPhone(1, 1).getStatusCode());
    }

    @Test
    void readPhone_whenDataIsNotValid_thenReturnHttpNOT_FOUND() {
        assertEquals(HttpStatus.NOT_FOUND, mainController.readPhone(1, 1).getStatusCode());
    }

    @Test
    void readUser_whenDataIsValid_thenReturnHttpOK() {
        mainController.createUser(new User());

        assertEquals(HttpStatus.OK, mainController.readUser(1).getStatusCode());
    }

    @Test
    void readUser_whenDataIsNotValid_thenReturnHttpNOT_FOUND() {
        assertEquals(HttpStatus.NOT_FOUND, mainController.readUser(1).getStatusCode());
    }

    @Test
    void readUserByName_whenDataIsValid_thenReturnHttpOK() {
        User user = new User();
        user.setName("Thor");
        mainController.createUser(user);

        User findUser = new User();
        findUser.setName("thor");

        assertEquals(HttpStatus.OK, mainController.readUserByName(findUser).getStatusCode());
    }

    @Test
    void readUserByName_whenDataIsNotValid_thenReturnHttpNOT_FOUND() {
        User user = new User();
        user.setName("Thor");
        mainController.createUser(user);

        User findUser = new User();
        findUser.setName("loki");

        assertEquals(HttpStatus.NOT_FOUND, mainController.readUserByName(findUser).getStatusCode());
    }

    @Test
    void readUserByName_whenDataIsNull_thenReturnHttpBAD_REQUEST() {
        assertEquals(HttpStatus.BAD_REQUEST, mainController.readUserByName(null).getStatusCode());
    }

    @Test
    void readPhoneByPhoneNumber_whenDataIsValid_thenReturnHttpOK() {
        Phone phone = new Phone();
        phone.setPhoneNumber("555358");

        Phone findPhone = new Phone();
        findPhone.setPhoneNumber("5553");

        mainController.createUser(new User());
        mainController.createPhone(phone, 1);

        assertEquals(HttpStatus.OK, mainController.readPhoneByPhoneNumber(1, findPhone).getStatusCode());
    }

    @Test
    void readPhoneByPhoneNumber_whenDataIsNotValid_thenReturnHttpNOT_FOUND() {
        Phone phone = new Phone();
        phone.setPhoneNumber("555358");

        Phone findPhone = new Phone();
        findPhone.setPhoneNumber("145");

        mainController.createUser(new User());
        mainController.createPhone(phone, 1);

        assertEquals(HttpStatus.NOT_FOUND, mainController.readPhoneByPhoneNumber(1, findPhone).getStatusCode());
    }

    @Test
    void readPhoneByPhoneNumber_whenDataIsNul_thenReturnHttpBAD_REQUEST() {
        assertEquals(HttpStatus.BAD_REQUEST, mainController.readPhoneByPhoneNumber(1, null).getStatusCode());
    }

    @Test
    void updateUser_whenDataIsValid_thenReturnHttpOK() {
        mainController.createUser(new User());
        User updatedUser = new User();
        updatedUser.setName("Mira");

        assertEquals(HttpStatus.OK, mainController.updateUser(1, updatedUser).getStatusCode());
        assertEquals(updatedUser, userService.read(1));
    }

    @Test
    void updateUser_whenDataIsNotValid_thenReturnHttpBAD_REQUEST() {
        assertEquals(HttpStatus.BAD_REQUEST, mainController.updateUser(2, new User()).getStatusCode());
    }

    @Test
    void updateUser_whenDataIsNULL_thenReturnHttpBAD_REQUEST() {
        assertEquals(HttpStatus.BAD_REQUEST, mainController.updateUser(1, null).getStatusCode());
    }

    @Test
    void updatePhone_whenDataIsValid_thenReturnHttpOK() {
        mainController.createUser(new User());
        mainController.createPhone(new Phone(), 1);
        Phone updatePhone = new Phone();
        updatePhone.setPhoneNumber("555789");

        assertEquals(HttpStatus.OK, mainController.updatePhone(1, 1, updatePhone).getStatusCode());
        assertEquals(updatePhone, phoneService.read(1));
    }

    @Test
    void updatePhone_whenDataIsNotValid_thenReturnHttpBAD_REQUEST() {
        assertEquals(HttpStatus.BAD_REQUEST, mainController.updatePhone(1, 1, new Phone()).getStatusCode());
    }

    @Test
    void updatePhone_whenDataIsNULL_thenReturnHttpBAD_REQUEST() {
        assertEquals(HttpStatus.BAD_REQUEST, mainController.updatePhone(1, 1, null).getStatusCode());
    }

    @Test
    void deleteUser_whenDataIsValid_thenReturnHttpOK() {
        mainController.createUser(new User());

        assertNotNull(userService.read(1));
        assertEquals(HttpStatus.OK, mainController.deleteUser(1).getStatusCode());
        assertNull(userService.read(1));
    }

    @Test
    void deleteUser_whenDataIsNotValid_thenReturnHttpBAD_REQUEST() {
        assertEquals(HttpStatus.BAD_REQUEST, mainController.deleteUser(1).getStatusCode());
    }

    @Test
    void deletePhone_whenDataIsValid_thenReturnHttpOK() {
        mainController.createUser(new User());
        mainController.createPhone(new Phone(), 1);

        assertNotNull(phoneService.read(1));
        assertFalse(userService.read(1).getPhones().isEmpty());
        assertEquals(HttpStatus.OK, mainController.deletePhone(1, 1).getStatusCode());
        assertNull(phoneService.read(1));
        assertTrue(userService.read(1).getPhones().isEmpty());
    }

    @Test
    void deletePhone_whenDataIsNotValid_thenReturnHttpBAD_REQUEST() {
        assertEquals(HttpStatus.BAD_REQUEST, mainController.deletePhone(1, 2).getStatusCode());
    }

}