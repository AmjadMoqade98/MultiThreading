package com.Exalt.MultiThreading.Mapper;

import com.Exalt.MultiThreading.Dao.CustomerDao;
import com.Exalt.MultiThreading.Dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    @Autowired
    ModelMapper modelMapper;

    public CustomerDao customerDtoToDao(CustomerDto customerDto) {
        return modelMapper.map(customerDto, CustomerDao.class);
    }

    public CustomerDto customerDaoToDto(CustomerDao customerDao) {
        return modelMapper.map(customerDao, CustomerDto.class);
    }
}
