package com.mobilapi.repository;

import com.mobilapi.domain.customer.Account;
import com.mobilapi.domain.customer.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    List<Location> findByAccount(Account account);
}
