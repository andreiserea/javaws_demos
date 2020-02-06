package com.aserea.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    private Integer id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String address;

    public User(Integer id, String username, String email, String firstname, String lastname, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, firstname, lastname, address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                username.equals(user.username) &&
                email.equals(user.email) &&
                firstname.equals(user.firstname) &&
                lastname.equals(user.lastname) &&
                Objects.equals(address, user.address);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static User fromString(String userAsString) {
        Pattern pattern = Pattern.compile("User\\{id=(.*), username='(.*)', email='(.*)', firstname='(.*)', lastname='(.*)', address='(.*)'\\}");
        Matcher matcher = pattern.matcher(userAsString);
        if (matcher.find()) {
            return new User(Integer.valueOf(matcher.group(1)), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5), matcher.group(6));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String args[]) {
        System.out.println(fromString(
            new User(1, "andrei", "a@a.com", "andrei", "serea", "bucuresti").toString()));
    }
}
