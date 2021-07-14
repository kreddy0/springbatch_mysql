package com.shi.springbatch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.shi.springbatch.controller.RestClient;
import com.shi.springbatch.model.Address;

@Component
public class AddressWriter implements ItemWriter<Address> {
	

	@Override
	public void write(List<? extends Address> addressWriter) throws Exception {
		
		RestClient.callCreateAddressAPI(addressWriter);
	}

}
