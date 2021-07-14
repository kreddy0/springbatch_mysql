package com.shi.springbatch.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.shi.springbatch.model.Address;
import com.shi.springbatch.model.Customer;

@Mapper
public interface CustomMapper {
	
	@Insert("insert into customer(last_name,first_name,phone,email,title,designation) values(#{last_name},#{first_name},#{phone},#{email},#{title},#{designation})")
	 void addCustomer(Customer customer);
	
	@Insert("insert into address(customerPhone,addressType,addressLine1,addressLine2,city,stateCode,zipcode,zipplus4,addressType2,addressLine12,addressLine22,city2,stateCode2,zipcode2,zipplus42) values(#{customerPhone},#{addressType},#{addressLine1},#{addressLine2},#{city},#{stateCode},#{zipcode},#{zipplus4},#{addressType2},#{addressLine12},#{addressLine22},#{city2},#{stateCode2},#{zipcode2},#{zipplus42})")
	void addAddress(Address address);
}
