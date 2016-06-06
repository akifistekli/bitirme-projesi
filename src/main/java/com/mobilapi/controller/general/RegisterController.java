package com.mobilapi.controller.general;


import com.mobilapi.controller.dto.UserDto;
import com.mobilapi.security.service.AuthenticationService;
import com.mobilapi.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegisterController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);


    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST, value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity saveNewUser(@RequestBody @Valid UserDto userDto) {

        accountService.saveAccount(userDto.createUser());
        authenticationService.authenticate(userDto.getEmail(), userDto.getPassword());

        return new ResponseEntity(HttpStatus.OK);
    }
}
