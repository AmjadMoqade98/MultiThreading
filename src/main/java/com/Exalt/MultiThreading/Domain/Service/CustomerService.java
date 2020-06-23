package com.Exalt.MultiThreading.Domain.Service;

import com.Exalt.MultiThreading.Domain.Dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    public List<CustomerDto> getCustomers();

    public CustomerDto getCustomer(String id);

    public CustomerDto addCustomer(CustomerDto customerDto);

    public CustomerDto updateCustomer(CustomerDto customerDto);

    public boolean deleteCustomer(String id);

    public void deleteCustomers();

}