package local.restphonebook.phonebook.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class User {

    private Integer id;
    private String name;
    private List<Phone> phoneList = new ArrayList<>();

    public void addPhoneToList(Phone phone) {
        phoneList.add(phone);
    }

}
