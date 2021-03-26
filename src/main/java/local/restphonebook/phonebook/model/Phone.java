package local.restphonebook.phonebook.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Phone {

    private Integer id;
    //TODO: private String name;
    private Long phoneNumber;
    private User user;

}
