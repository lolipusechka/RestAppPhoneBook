package local.restphonebook.phonebook.model;

import java.util.Objects;


public class Phone {

    private Integer id;
    private String phoneNumber;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id) && Objects.equals(phoneNumber, phone.phoneNumber) && Objects.equals(userId, phone.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, userId);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userId=" + userId +
                '}';
    }
}
