package com.mobilapi.repository;


import com.mobilapi.domain.customer.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account,Long> {

    Account findByEmail(String email);
}
