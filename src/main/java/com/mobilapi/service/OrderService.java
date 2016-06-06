package com.mobilapi.service;

import com.mobilapi.domain.customer.Account;
import com.mobilapi.domain.customer.Location;
import com.mobilapi.domain.order.Order;
import com.mobilapi.domain.product.Product;
import com.mobilapi.repository.OrderRepository;
import com.mobilapi.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LocationService locationService;

    @Autowired
    private AuthenticationService authenticationService;

    public List<Order> getAllOrderByAccount(Account account) {

        return orderRepository.findAllByAccount(account);
    }


    public Order createOrder(List<Product> product, Long location_id, Long price) {

        Account account = authenticationService.getCurrentAccount();
        Location location = locationService.getLocationById(location_id);

        Order order = new Order();
        order.setAccount(account);
        order.setLocation(location);
        order.setProducts(product);
        order.setPrice(price);

        return orderRepository.save(order);
    }

}
