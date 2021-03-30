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
        phoneList.add(phone);

        phoneService.create(phone);

        assertEquals(phoneList, phoneService.readByPhoneNumber("5"));
        assertEquals(phoneList, phoneService.readByPhoneNumber("55"));
        assertEquals(phoneList, phoneService.readByPhoneNumber("555"));
        assertEquals(phoneList, phoneService.readByPhoneNumber("555111"));
    }

    @Test
    void readByPhoneNumber_whenDataIsNotValidAndMapHaveValues_thenReturnEmptyListOfPhone() {
        Phone phone1 = new Phone();
        phone1.setPhoneNumber("555111");
        Phone phone2 = new Phone();
        phone2.setPhoneNumber("654");

        phoneService.create(phone1);
        phoneService.create(phone2);

        assertTrue(phoneService.readByPhoneNumber("").isEmpty());
        assertTrue(phoneService.readByPhoneNumber("12").isEmpty());
        assertTrue(phoneService.readByPhoneNumber("555112").isEmpty());
    }

    @Test
    void readByPhoneNumber_whenMapDoNotHaveValues_thenReturnEmptyListOfPhone() {
        assertTrue(phoneService.readByPhoneNumber("555112").isEmpty());
    }

    @Test
    void update_whenDataIsValid_thenReturnTrueAndUpdateValueInMap() {
        List<Phone> originalList = new ArrayList<>();
        List<Phone> updatedList = new ArrayList<>();

        Phone originalPhone = new Phone();
        originalPhone.setPhoneNumber("555123");

        Phone updatedPhone = new Phone();
        updatedPhone.setPhoneNumber("123555");

        Phone exceptedPhone = new Phone();
        exceptedPhone.setId(1);
        exceptedPhone.setPhoneNumber("123555");

        originalList.add(originalPhone);
        updatedList.add(updatedPhone);

        phoneService.create(originalPhone);

        assertEquals(originalList, phoneService.readByPhoneNumber("555123"));
        assertTrue(phoneService.update(updatedPhone, 1));
        assertEquals(updatedList, phoneService.readByPhoneNumber("123555"));
        assertEquals(exceptedPhone, phoneService.read(1));
    }

    @Test
    void update_whenDataIsNotValid_thenReturnFalseAndDoNotUpdateValueInMap() {
        List<Phone> originalList = new ArrayList<>();
        List<Phone> updatedList = new ArrayList<>();

        Phone originalPhone = new Phone();
        originalPhone.setPhoneNumber("555123");

        Phone updatedPhone = new Phone();
        updatedPhone.setPhoneNumber("123555");

        Phone exceptedPhone = new Phone();
        exceptedPhone.setId(1);
        exceptedPhone.setPhoneNumber("123555");

        originalList.add(originalPhone);
        updatedList.add(updatedPhone);

        phoneService.create(originalPhone);

        assertEquals(originalList, phoneService.readByPhoneNumber("555123"));
        assertFalse(phoneService.update(updatedPhone, 5));
        assertNotEquals(updatedList, phoneService.readByPhoneNumber("123555"));
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