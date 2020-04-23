package com.camunda.workers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

import com.camunda.bean.Process_Status;
import com.camunda.util.DbOperations;

public class FailedMsisdnReviewWorker implements WorkerInterface{

	private final static Logger LOGGER = Logger.getLogger(FailedMsisdnReviewWorker.class.getName());

	public void executeBusinessLogic(ExternalTask externalTask, ExternalTaskService externalTaskService) {
		LOGGER.info("Failed Msisdn Review started");
		try {
			// Put your business logic here
			Process_Status dbobj = new Process_Status();
			// Get a process variable
			/*String custId = (String) externalTask.getVariable("custId");
	            String msisdn = (String) externalTask.getVariable("msisdn");
	            String protype = (String) externalTask.getVariable("protype");
			 */	String instId= externalTask.getProcessInstanceId();
			 //LOGGER.info("Failed Msisdn Review custId : '" + custId + "' , msisdn : '" + msisdn + "' and protype : '"+ protype +"...");

			 dbobj.setCmreqid(instId);
			 dbobj.setCmstatus("Review Process Failed");

			 //Insert values into msisdn table
			 DbOperations.updateRecord(dbobj);

		} catch(Exception e){
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		// Complete the task
		externalTaskService.complete(externalTask);
	}

}
