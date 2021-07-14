package com.shi.springbatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.shi.springbatch.controller.RestClient;
import com.shi.springbatch.model.Customer;

@Component
public class itemWriter implements ItemWriter<Customer>{


@Override
public void write(List<? extends Customer> items) throws Exception {

	RestClient.callCreateCustomerAPI(items);
	
}
	
	
}
