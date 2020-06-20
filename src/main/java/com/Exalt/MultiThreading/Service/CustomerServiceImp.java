package com.Exalt.MultiThreading.Service;

import com.Exalt.MultiThreading.Dao.CustomerDao;
import com.Exalt.MultiThreading.Dto.CustomerDto;
import com.Exalt.MultiThreading.Mapper.CustomerMapper;
import com.Exalt.MultiThreading.Repository.CustomerRepositoryImp;
import com.Exalt.MultiThreading.Dom.ServerDomainService;
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

    public List<CustomerDto> getCustomers() {
        List<CustomerDao> customerDaos = new ArrayList<>();
        List<CustomerDto> customerDtos = new ArrayList<>();
        customerRepositoryImp.findAll().forEach(customerDaos::add);

        for (CustomerDao customerDao : customerDaos) {
            customerDtos.add(customerMapper.customerDaoToDto(customerDao));
        }
        return customerDtos;
    }

    public CustomerDto getCustomer(String id) {
        return customerMapper.customerDaoToDto(customerRepositoryImp.findOne(id));
    }

    public void addCustomer(CustomerDto customerDto) {
        customerDto.setId(friendlyId.createFriendlyId());
        customerRepositoryImp.save(customerMapper.customerDtoToDao(customerDto));
    }

    public void updateCustomer(CustomerDto customerDto) {
        customerRepositoryImp.save(customerMapper.customerDtoToDao(customerDto));
    }

    public void deleteCustomer(String id) {
        customerRepositoryImp.delete(id);
    }

    public void deleteCustomers() {
        customerRepositoryImp.deleteAll();
    }

    public void rentSpace(String customerId, int space) {
        serverDomainService.RentServer(customerId, space);
    }
}
