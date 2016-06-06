package com.mobilapi.bulider;


import com.mobilapi.domain.customer.Account;
import com.mobilapi.domain.enums.UserRole;
import org.apache.commons.lang3.RandomStringUtils;

public class AccountBuilder {

    private String firstName = RandomStringUtils.random(10);

    private String lastName = RandomStringUtils.random(10);

    private String email = RandomStringUtils.random(10);

    private String password = RandomStringUtils.random(10);

    private String city = RandomStringUtils.random(10);

    private String district = RandomStringUtils.random(10);

    private UserRole userRole = UserRole.User;

    public AccountBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AccountBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AccountBuilder email(String email) {
        this.email = email;
        return this;
    }

    public AccountBuilder password(String password) {
        this.password = password;
        return this;
    }

    public AccountBuilder city(String city) {
        this.city = city;
        return this;
    }

    public AccountBuilder district(String district) {
        this.district = district;
        return this;
    }

    public AccountBuilder userRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public Account bulid(){
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);
        account.setPassword(password);
        account.setCity(city);
        account.setDistrict(district);
        account.setUserRole(userRole);

        return account;
    }

}
