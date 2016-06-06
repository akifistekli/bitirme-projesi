package com.mobilapi.repository;

import com.mobilapi.domain.customer.Account;
import com.mobilapi.domain.order.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    List<Order>  findAllByAccount(Account account);
}
