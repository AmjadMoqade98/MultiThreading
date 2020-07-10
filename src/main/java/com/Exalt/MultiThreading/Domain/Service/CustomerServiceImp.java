package com.Exalt.MultiThreading.Domain.Service;

import com.Exalt.MultiThreading.Domain.Dao.CustomerDao;
import com.Exalt.MultiThreading.Application.Dto.CustomerDto;
import com.Exalt.MultiThreading.Domain.Mapper.CustomerMapper;
import com.Exalt.MultiThreading.Domain.Repository.CustomerRepository;
import com.Exalt.MultiThreading.Domain.validation.CustomerValidation;
import com.Exalt.MultiThreading.Domain.validation.RentValidation;
import com.devskiller.friendly_id.FriendlyId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    ServerProvider serverDomainService;

    @Autowired
    FriendlyId friendlyId;

    @Autowired
    CustomerValidation customerValidation;

    @Autowired
    RentValidation rentValidation;

    public List<CustomerDto> getCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        PageRequest pagable = new PageRequest(0, 4, new Sort(Sort.Direction.ASC, "reservedSpace"));
        customerRepository.findAll().forEach(customerDao -> {
            customerDtos.add(customerMapper.customerDaoToDto(customerDao));
        });
        return customerDtos;
    }

    public CustomerDto getCustomer(String id) {
        if (customerValidation.validateCustomerId(id)) {
            long start;
            CustomerDto customerDto;
            start = System.currentTimeMillis();
            customerDto = customerMapper.customerDaoToDto(customerRepository.findOne(id));
            System.out.println(System.currentTimeMillis() - start);
            start = System.currentTimeMillis();
            customerDto = customerMapper.customerDaoToDto(customerRepository.findOne(id));
            System.out.println(System.currentTimeMillis() - start);
            return customerDto;
        }
        return null;
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {
        customerDto.setId(friendlyId.createFriendlyId());
        customerRepository.save(customerMapper.customerDtoToDao(customerDto));
        return customerDto;
    }

    public CustomerDto updateCustomer(CustomerDto customerDto) {
        CustomerDao customerDao = customerRepository.save(customerMapper.customerDtoToDao(customerDto));
        return customerMapper.customerDaoToDto(customerDao);
    }

    public boolean deleteCustomer(String id) {
        if (customerValidation.validateCustomerId(id) == false) {
            return false;
        }
        customerRepository.delete(id);
        return true;
    }

    public void deleteCustomers() {
        customerRepository.deleteAll();
    }
}
