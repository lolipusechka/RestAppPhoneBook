package local.restphonebook.phonebook.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {

    private Integer id;
    private String name;
    private List<Phone> phoneList;

}
