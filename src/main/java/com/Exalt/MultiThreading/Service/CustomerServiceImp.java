package com.Exalt.MultiThreading.Service;

import com.Exalt.MultiThreading.Dao.CustomerDao;
import com.Exalt.MultiThreading.Dto.CustomerDto;
import com.Exalt.MultiThreading.Mapper.CustomerMapper;
import com.Exalt.MultiThreading.Repository.CustomerRepositoryImp;
import com.Exalt.MultiThreading.Dom.ServerDomainService;
import com.Exalt.MultiThreading.validation.CustomerValidation;
import com.Exalt.MultiThreading.validation.RentValidation;
import com.devskiller.friendly_id.FriendlyId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    CustomerRepositoryImp customerRepositoryImp;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    ServerDomainService serverDomainService;

    @Autowired
    FriendlyId friendlyId;

    @Autowired
    CustomerValidation customerValidation;

    @Autowired
    RentValidation rentValidation;

    public List<CustomerDto> getCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        customerRepositoryImp.findAll().forEach(customerDao->{
            customerDtos.add(customerMapper.customerDaoToDto(customerDao));
        });
        return customerDtos;
    }

    public CustomerDto getCustomer(String id) {
        if (customerValidation.validateCustomerId(id) == false) {
            return null;
        }
        return customerMapper.customerDaoToDto(customerRepositoryImp.findOne(id));
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        customerDto.setId(friendlyId.createFriendlyId());
        customerRepositoryImp.save(customerMapper.customerDtoToDao(customerDto));
        return customerDto;
    }

    public CustomerDto updateCustomer(CustomerDto customerDto) {
        CustomerDao customerDao = customerRepositoryImp.save(customerMapper.customerDtoToDao(customerDto));
        return customerMapper.customerDaoToDto(customerDao);
    }

    public void deleteCustomer(String id) {
        customerRepositoryImp.delete(id);
    }

    public void deleteCustomers() {
        customerRepositoryImp.deleteAll();
    }


}
