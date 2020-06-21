package com.Exalt.MultiThreading.Service;

import com.Exalt.MultiThreading.Dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    public List<CustomerDto> getCustomers();

    public CustomerDto getCustomer(String id);

    public CustomerDto addCustomer(CustomerDto customerDto);

    public CustomerDto updateCustomer(CustomerDto customerDto);

    public void deleteCustomer(String id);

    public void deleteCustomers();

}