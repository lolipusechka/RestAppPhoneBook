package local.restphonebook.phonebook.service.impl;

import local.restphonebook.phonebook.model.Phone;
import local.restphonebook.phonebook.service.PhoneService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final Map<Integer, Phone> phoneMap = new HashMap<>();
    private final AtomicInteger phoneIdHolder = new AtomicInteger();


    @Override
    public void create(Long phoneNumber) {
        final Phone phone = new Phone();
        final Integer idPhone = phoneIdHolder.incrementAndGet();
        phone.setId(idPhone);
        phone.setPhoneNumber(phoneNumber);
        phoneMap.put(idPhone, phone);
    }

    @Override
    public List<Phone> readAll() {
        return new ArrayList<>(phoneMap.values());
    }

    @Override
    public Phone read(Integer id) {
        return phoneMap.get(id);
    }

    @Override
    public boolean update(Phone phone, Integer id, Long phoneNumber) {
        if (phoneMap.containsKey(id)) {
            phone.setId(id);
            phone.setPhoneNumber(phoneNumber);
            phoneMap.put(id, phone);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return phoneMap.remove(id) != null;
    }
}
