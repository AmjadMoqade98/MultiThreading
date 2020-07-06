package com.Exalt.MultiThreading.Application.Controller;

import com.Exalt.MultiThreading.Application.Dto.CustomerDto;
import com.Exalt.MultiThreading.Domain.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity getCustomers() {
        return new ResponseEntity(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable("id") String id) {
        CustomerDto customerDto = customerService.getCustomer(id);
        if (customerDto == null) {
            return new ResponseEntity("customer not exist", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(customerDto, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity AddCustomer(@RequestBody final CustomerDto customerDto) {
        return new ResponseEntity(customerService.addCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity UpdateCustomer(@RequestBody final CustomerDto customerDto, @PathVariable("id") String id) {
        customerDto.setId(id);
        return new ResponseEntity(customerService.updateCustomer(customerDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") String id) {

        if (customerService.deleteCustomer(id) == false) {
            return new ResponseEntity("customer not exist", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("customer deleted successfully", HttpStatus.OK);
        }
    }

    @DeleteMapping()
    public ResponseEntity deleteCustomers() {
        customerService.deleteCustomers();
        return new ResponseEntity<>("customers deleted successfully", HttpStatus.OK);
    }
}