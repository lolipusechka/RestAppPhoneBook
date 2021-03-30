package local.restphonebook.phonebook.service.impl;

import local.restphonebook.phonebook.model.User;
import local.restphonebook.phonebook.service.UserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    UserService userService = new UserServiceImpl();

    @Test
    void create_whenDataIsValid_thenUserPutToMap() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        userList.add(user);

        userService.create(user);

        assertFalse(userService.readAll().isEmpty());
        assertEquals(userList, userService.readAll());
    }

    @Test
    void readAll_whenMapHaveValues_thenReturnListOfUsers() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        userList.add(user1);
        userList.add(user2);

        userService.create(user1);
        userService.create(user2);

        assertEquals(userList, userService.readAll());
    }

    @Test
    void readAll_whenMapDoNotHaveValues_thenListIsEmpty() {
        assertTrue(userService.readAll().isEmpty());
    }

    @Test
    void read_whenDataIsValidAndMapHaveValues_thenReturnUser() {
        User resultUser = new User();
        User exceptedUser = new User();
        exceptedUser.setId(1);

        userService.create(resultUser);

        assertEquals(exceptedUser, userService.read(1));
    }

    @Test
    void read_whenDataIsNotValidAndMapHaveValues_thenReturnNull() {
        User user = new User();

        userService.create(user);

        assertNull(userService.read(2));
    }

    @Test
    void read_whenMapDoNotHaveValues_thenReturnNull() {
        assertNull(userService.read(1));
    }

    @Test
    void readByName_whenDataIsValidAndMapHaveValues_thenReturnListOfUsers() {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("Thor");
        userList.add(user);

        userService.create(user);

        assertEquals(userList, userService.readByName("T"));
        assertEquals(userList, userService.readByName("Th"));
        assertEquals(userList, userService.readByName("Tho"));
        assertEquals(userList, userService.readByName("Thor"));
        assertEquals(userList, userService.readByName("th"));
    }

    @Test
    void readByName_whenDataIsNotValidAndMapHaveValues_thenReturnEmptyListOfUsers() {
        User user = new User();
        user.setName("Thor");

        assertTrue(userService.readByName("").isEmpty());
        assertTrue(userService.readByName("l").isEmpty());
        assertTrue(userService.readByName("Loki").isEmpty());
    }

    @Test
    void readByName_whenMapDoNotHaveValues_thenReturnEmptyListOfUsers() {
        assertTrue(userService.readByName("Thor").isEmpty());
    }

    @Test
    void update_whenDataIsValid_thenReturnTrueAndUpdateValueInMap() {
        List<User> originalList = new ArrayList<>();
        List<User> updatedList = new ArrayList<>();

        User originalUser = new User();
        originalUser.setName("Thor");

        User updatedUser = new User();
        updatedUser.setName("Thor Odin's Son");

        User exceptedUser = new User();
        exceptedUser.setId(1);
        exceptedUser.setName("Thor Odin's Son");

        originalList.add(originalUser);
        updatedList.add(updatedUser);

        userService.create(originalUser);

        assertEquals(originalList, userService.readByName("Thor"));
        assertTrue(userService.update(updatedUser, 1));
        assertEquals(updatedList, userService.readByName("Thor Odin's Son"));
        assertEquals(exceptedUser, userService.read(1));
    }

    @Test
    void update_whenDataIsNotValid_thenReturnFalseAndDoNotUpdateValueInMap() {
        List<User> originalList = new ArrayList<>();
        List<User> updatedList = new ArrayList<>();

        User originalUser = new User();
        originalUser.setName("Thor");

        User updatedUser = new User();
        updatedUser.setName("Thor Odin's Son");

        User exceptedUser = new User();
        exceptedUser.setId(1);
        exceptedUser.setName("Thor Odin's Son");

        originalList.add(originalUser);
        updatedList.add(updatedUser);

        userService.create(originalUser);

        assertEquals(originalList, userService.readByName("Thor"));
        assertFalse(userService.update(updatedUser, 5));
        assertNotEquals(updatedList, userService.readByName("Thor Odin's Son"));
        assertNotEquals(exceptedUser, userService.read(1));
    }

    @Test
    void delete_whenDataIsValid_thenReturnTrueAndRemovePhoneFromMap() {
        User user = new User();

        userService.create(user);

        assertTrue(userService.delete(1));
        assertNull(userService.read(1));
    }

    @Test
    void delete_whenDataIsNotValid_thenReturnFalseAndRemovePhoneFromMap() {
        User user = new User();

        userService.create(user);

        assertFalse(userService.delete(55));
        assertNotNull(userService.read(1));
    }
}