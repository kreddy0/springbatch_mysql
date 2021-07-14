package com.shi.springbatch.config;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.shi.springbatch.model.Address;
import com.shi.springbatch.model.Customer;
import com.shi.springbatch.processor.AddressProcessor;
import com.shi.springbatch.processor.CustomerProcessor;
import com.shi.springbatch.writer.AddressWriter;
import com.shi.springbatch.writer.itemWriter;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired 
	public StepBuilderFactory stepBuilderFactory;

//	@Autowired
//	public DataSource dataSource; 

	@Autowired
	itemWriter customerwriter;
	
	@Autowired
	AddressWriter addressWriter;

	
	    @Bean
		public FlatFileItemReader<Customer> itemReader(){
		
			FlatFileItemReader<Customer> flatfileItemReader = new FlatFileItemReader<>();
			flatfileItemReader.setResource(new ClassPathResource("Contacts.csv"));
			flatfileItemReader.setName("CSV-Reader");
			flatfileItemReader.setLinesToSkip(1);
			flatfileItemReader.setLineMapper(lineMapper());
			return flatfileItemReader;
			
		}
	 
	 @Bean
	public LineMapper<Customer> lineMapper(){
			DefaultLineMapper<Customer> defaultLineMapper = new DefaultLineMapper<>();
			DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
			
			lineTokenizer.setDelimiter(",");
			lineTokenizer.setStrict(false);
			
			lineTokenizer.setNames("last_name", "first_name", "phone","email", "title", "designation");
			
			BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<Customer>();
			fieldSetMapper.setTargetType(Customer.class);
			
			defaultLineMapper.setLineTokenizer(lineTokenizer);
			defaultLineMapper.setFieldSetMapper(fieldSetMapper);
			
			return defaultLineMapper;
	 

	
}
	 @Bean
	 public CustomerProcessor custProcessor(){
		 return new CustomerProcessor();
	 }

	 
//	@Bean
//	public JdbcBatchItemWriter<Customer> itemWriter(){
//		JdbcBatchItemWriter<Customer> jdbcWriter = new JdbcBatchItemWriter<Customer>();
//		jdbcWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Customer>());
//		jdbcWriter.setSql("INSERT INTO customer (last_name,first_name,phone,email,title,designation) VALUES (:last_name,:first_name,:phone,:email,:title,:designation)");
//		jdbcWriter.setDataSource(this.dataSource);
//		return jdbcWriter;
//		
//	}
	 
	
	@Bean
	public Job importUserJob(){

		return jobBuilderFactory.get("importUserJob")
						.incrementer(new RunIdIncrementer())
						.start(step1())
						.next(step2())
						.build();
			  
	}
	 
	
	@Bean
	public Step step1(){
		 return stepBuilderFactory.get("step1")
				 	.<Customer,Customer>chunk(100)
				    .reader(itemReader())
				    .processor(custProcessor())
				    .writer(customerwriter)
				    .build();

	}
	
	 @Bean
	 public AddressProcessor addreProcessor(){
		 return new AddressProcessor();
	 }
	 
	
	@Bean
	public Step step2(){
		
		return stepBuilderFactory.get("step2")
			 	.<Address,Address>chunk(100)
			    .reader(addressReader())
			    .processor(addreProcessor())
			    .writer(addressWriter)
			    .build();
	}
	
	    @Bean
		public FlatFileItemReader<Address> addressReader(){
		
			FlatFileItemReader<Address> flatfileItemReader = new FlatFileItemReader<>();
			flatfileItemReader.setResource(new ClassPathResource("Addresses.dat"));
			flatfileItemReader.setName("Address-Reader");
			flatfileItemReader.setLineMapper(addressMapper());
			return flatfileItemReader;
			
		}
	 
	 
	 @Bean
	 public LineMapper<Address> addressMapper(){
		 DefaultLineMapper<Address> defaultLineMapper = new DefaultLineMapper<>();
		 FixedLengthTokenizer fixedLengthTokenizer = new FixedLengthTokenizer();
		
		 fixedLengthTokenizer.setStrict(false);
		 
		 fixedLengthTokenizer.setNames("customerPhone","addressType","addressLine1","addressLine2","city","stateCode","zipcode","zipplus4","addressType2","addressLine12","addressLine22","city2","stateCode2","zipcode2","zipplus42");
		 fixedLengthTokenizer.setColumns(new Range(1,10),new Range(11,11),new Range(12,41),new Range(42,71),new Range(72,86),new Range(87,88),new Range(89,93),new Range(94,97),new Range(98,98),new Range(99,128),new Range(129,158),new Range(159,173),new Range(174,175),new Range(176,180),new Range(181,184));
		 
		 BeanWrapperFieldSetMapper<Address> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
			fieldSetMapper.setTargetType(Address.class);
		 
		 defaultLineMapper.setLineTokenizer(fixedLengthTokenizer);
		 defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
		 
	 }
}
