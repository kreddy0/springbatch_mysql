package com.shi.springbatch.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/load")
public class BatchLoadController {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchLoadController.class);
	
	@GetMapping
	public BatchStatus load() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		
		
		
		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters(maps);
		
		JobExecution jobExecution = jobLauncher.run(job, parameters);
		
		LOGGER.info("JobExecution: "+ jobExecution.getStatus());
		
		LOGGER.info("Batch is running .");
		while(( jobExecution).isRunning()){
			LOGGER.info(" .");
		}
		
		return jobExecution.getStatus();
	} 
}
