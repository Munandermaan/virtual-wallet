package com.fabhotel.assignment.virtualwallet.controller;


import com.fabhotel.assignment.virtualwallet.exception.CustomerAlreadyCreatedException;
import com.fabhotel.assignment.virtualwallet.exception.CustomerDoesNotExistException;
import com.fabhotel.assignment.virtualwallet.models.Customer;
import com.fabhotel.assignment.virtualwallet.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ResponseStatus(value = HttpStatus.OK)
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping("/api/customer/{email}")
    public Customer findCustomerByEmail(@PathVariable("email") String email) throws CustomerDoesNotExistException {

        return customerService.getCustomerEmail(email);
    }

    @PostMapping("/api/customer")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid Customer customer) throws CustomerAlreadyCreatedException {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer.getUserId(), HttpStatus.OK);
    }

}