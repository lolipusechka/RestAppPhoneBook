package local.restphonebook.phonebook.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Phone {

    private Integer id;
    //TODO: private String name;
    private String phoneNumber;
    private Integer userId;
    private User user;

}
