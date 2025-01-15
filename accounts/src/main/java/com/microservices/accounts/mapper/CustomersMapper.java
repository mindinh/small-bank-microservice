package com.microservices.accounts.mapper;

import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.CustomerEntity;

public class CustomersMapper {

    public static CustomerDto mapToCustomerDto(CustomerEntity customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNo(customer.getMobileNo());
        return customerDto;
    }

    public static CustomerEntity mapToCustomer(CustomerDto customerDto, CustomerEntity customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNo(customerDto.getMobileNo());
        return customer;
    }

}
