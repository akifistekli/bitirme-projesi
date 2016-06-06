package com.mobilapi.controller.user;

import com.mobilapi.controller.dto.OrderDto;
import com.mobilapi.domain.customer.Account;
import com.mobilapi.security.service.AuthenticationService;
import com.mobilapi.service.AccountService;
import com.mobilapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController extends SecureBaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCurrentAccountOrder() {

        //Account account = accountService.findByEmail("omnifood@gmail.com");
        Account account = accountService.findByEmail(authenticationService.getCurrentAccount().getEmail());
        if (account.equals(null)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity(orderService.getAllOrderByAccount(account), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void createOrder(@RequestBody OrderDto orderDto) {

        orderService.createOrder(orderDto.getProducts(), orderDto.getLocation_id(), orderDto.getPrice());
    }
}
