package local.restphonebook.phonebook.service.impl;

import local.restphonebook.phonebook.model.Phone;
import local.restphonebook.phonebook.service.PhoneService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhoneServiceImplTest {

    PhoneService phoneService = new PhoneServiceImpl();

    @Test
    void create_whenDataIsValid_thenPhonePutToMap() {
        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phoneList.add(phone);

        phoneService.create(phone);

        assertFalse(phoneService.readAll().isEmpty());
        assertEquals(phoneList, phoneService.readAll());
    }

    @Test
    void readAll_whenMapHaveValues_thenReturnListOfPhones() {
        List<Phone> phoneList = new ArrayList<>();
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        phoneList.add(phone1);
        phoneList.add(phone2);

        phoneService.create(phone1);
        phoneService.create(phone2);

        assertEquals(phoneList, phoneService.readAll());
    }

    @Test
    void readAll_whenMapDoNotHaveValues_thenListIsEmpty() {
        assertTrue(phoneService.readAll().isEmpty());
    }

    @Test
    void read_whenDataIsValidAndMapHaveValues_thenReturnUser() {
        Phone resultPhone = new Phone();
        Phone exceptedPhone = new Phone();
        exceptedPhone.setId(1);

        phoneService.create(resultPhone);

        assertEquals(exceptedPhone, phoneService.read(1));
    }

    @Test
    void read_whenDataIsNotValidAndMapHaveValues_thenReturnNull() {
        Phone phone = new Phone();

        phoneService.create(phone);

        assertNull(phoneService.read(2));
    }

    @Test
    void read_whenMapDoNotHaveValues_thenReturnNull() {
        assertNull(phoneService.read(1));
    }

    @Test
    void readByPhoneNumber_whenDataIsValidAndMapHaveValues_thenReturnListOfPhone() {
        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phone.setPhoneNumber("555111");
        phone.setUserId(1);
        phoneList.add(phone);

        phoneService.create(phone);

        assertEquals(phoneList, phoneService.readByPhoneNumber("5", 1));
        assertEquals(phoneList, phoneService.readByPhoneNumber("55", 1));
        assertEquals(phoneList, phoneService.readByPhoneNumber("555", 1));
        assertEquals(phoneList, phoneService.readByPhoneNumber("555111", 1));
    }

    @Test
    void readByPhoneNumber_whenDataIsNotValidAndMapHaveValues_thenReturnEmptyListOfPhone() {
        Phone phone1 = new Phone();
        phone1.setPhoneNumber("555111");
        phone1.setUserId(1);
        Phone phone2 = new Phone();
        phone2.setPhoneNumber("654");
        phone2.setUserId(2);

        phoneService.create(phone1);
        phoneService.create(phone2);

        assertTrue(phoneService.readByPhoneNumber("", 1).isEmpty());
        assertTrue(phoneService.readByPhoneNumber("12", 1).isEmpty());
        assertTrue(phoneService.readByPhoneNumber("555112", 2).isEmpty());
    }

    @Test
    void readByPhoneNumber_whenMapDoNotHaveValues_thenReturnEmptyListOfPhone() {
        assertTrue(phoneService.readByPhoneNumber("555112", 1).isEmpty());
    }

    @Test
    void update_whenDataIsValid_thenReturnTrueAndUpdateValueInMap() {
        List<Phone> originalList = new ArrayList<>();
        List<Phone> updatedList = new ArrayList<>();

        Phone originalPhone = new Phone();
        originalPhone.setPhoneNumber("555123");
        originalPhone.setUserId(1);

        Phone updatedPhone = new Phone();
        updatedPhone.setPhoneNumber("123555");
        updatedPhone.setUserId(1);

        Phone exceptedPhone = new Phone();
        exceptedPhone.setId(1);
        exceptedPhone.setPhoneNumber("123555");
        exceptedPhone.setUserId(1);

        originalList.add(originalPhone);
        updatedList.add(updatedPhone);

        phoneService.create(originalPhone);

        assertEquals(originalList, phoneService.readByPhoneNumber("555123", 1));
        assertTrue(phoneService.update(updatedPhone, 1));
        assertEquals(updatedList, phoneService.readByPhoneNumber("123555", 1));
        assertEquals(exceptedPhone, phoneService.read(1));
    }

    @Test
    void update_whenDataIsNotValid_thenReturnFalseAndDoNotUpdateValueInMap() {
        List<Phone> originalList = new ArrayList<>();
        List<Phone> updatedList = new ArrayList<>();

        Phone originalPhone = new Phone();
        originalPhone.setPhoneNumber("555123");
        originalPhone.setUserId(1);

        Phone updatedPhone = new Phone();
        updatedPhone.setPhoneNumber("123555");
        updatedPhone.setUserId(1);

        Phone exceptedPhone = new Phone();
        exceptedPhone.setId(1);
        exceptedPhone.setPhoneNumber("123555");
        exceptedPhone.setUserId(1);

        originalList.add(originalPhone);
        updatedList.add(updatedPhone);

        phoneService.create(originalPhone);

        assertEquals(originalList, phoneService.readByPhoneNumber("555123", 1));
        assertFalse(phoneService.update(updatedPhone, 5));
        assertNotEquals(updatedList, phoneService.readByPhoneNumber("123555", 1));
        assertNotEquals(exceptedPhone, phoneService.read(1));
    }

    @Test
    void delete_whenDataIsValid_thenReturnTrueAndRemovePhoneFromMap() {
        Phone phone = new Phone();

        phoneService.create(phone);

        assertTrue(phoneService.delete(1));
        assertNull(phoneService.read(1));
    }

    @Test
    void delete_whenDataIsNotValid_thenReturnFalseAndRemovePhoneFromMap() {
        Phone phone = new Phone();

        phoneService.create(phone);

        assertFalse(phoneService.delete(55));
        assertNotNull(phoneService.read(1));
    }
}