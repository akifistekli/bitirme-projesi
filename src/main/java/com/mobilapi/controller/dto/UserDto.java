package com.mobilapi.controller.dto;


import com.mobilapi.annotations.EmailValidator;
import com.mobilapi.domain.customer.Account;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserDto {

    private String firstName;

    private String lastName;

    @NotEmpty
    @Email
    @EmailValidator
    private String email;

    @Size(min = 6, max = 12)
    private String password;

    private String city;

    private String district;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


    public Account createUser() {

        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);
        account.setPassword(password);
        account.setCity(city);
        account.setDistrict(district);

        return account;
    }
}
