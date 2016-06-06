package com.mobilapi.controller.user;


import com.mobilapi.domain.customer.Account;
import com.mobilapi.domain.customer.Location;
import com.mobilapi.service.AccountService;
import com.mobilapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController extends SecureBaseController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private LocationService locationService;


    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createLocationByAccount(@RequestBody Location location, Principal principal) {
        Account account = accountService.findByEmail(principal.getName());
        if(account.equals(null)){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }else {
            location.setAccount(account);
            locationService.createNewLocation(location);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getLocations", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getLocationsByAccount() {

        return new ResponseEntity(locationService.getAllLocationByAccount(), HttpStatus.OK);
    }


}
