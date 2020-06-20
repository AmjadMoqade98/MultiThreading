package com.Exalt.MultiThreading.Controller;

import com.Exalt.MultiThreading.Dto.CustomerDto;
import com.Exalt.MultiThreading.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public List<CustomerDto> getCustomers() {
        return customerService.getCustomers();
    }


    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable("id") String id) {
            return customerService.getCustomer(id) ;
    }

    @PostMapping
    public void AddCustomer(@RequestBody final CustomerDto customerDto) {
        System.out.println(customerDto.toString());
        customerService.addCustomer(customerDto) ;
    }

    @PutMapping("/{id}")
    public void UpdateCustomer(@RequestBody final CustomerDto customerDto,@PathVariable("id") String id ) {
        customerDto.setId(id);
        customerService.updateCustomer(customerDto) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBundle(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity deleteBundles() {
        customerService.deleteCustomers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/rent")
    public ResponseEntity RentServer(@RequestParam("space") int space , @PathVariable("id") String id ){
        customerService.rentSpace(id , space);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
