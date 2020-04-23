package com.camunda.workers;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.camunda.bean.Process_Status;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

import com.camunda.bean.MSISDN;
import com.camunda.util.DbOperations;

public class AssignPrepaidMsIsdnWorker implements WorkerInterface {

    private final static Logger LOGGER = Logger.getLogger(AssignPrepaidMsIsdnWorker.class.getName());

    public void executeBusinessLogic(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        LOGGER.info("Assign Prepaid MSISDN started");
        try {
            // Put your business logic here
            MSISDN dbobj = new MSISDN();
            // Get a process variable
            String custId = (String) externalTask.getVariable("custId").toString();
            String msisdn = (String) externalTask.getVariable("msisdn");
            String protype = (String) externalTask.getVariable("protype");

            LOGGER.info("Assign Prepaid MSISDN. custId : '" + custId + "' , msisdn : '" + msisdn + "' and protype : '"+ protype +"...");

            dbobj.setCustId(custId);
            dbobj.setMsisdn(msisdn);
            dbobj.setProtype(protype);
            dbobj.setStatus("ASSIGNED");
            
            //Insert values into msisdn table
            DbOperations.createRecord(dbobj);

            // Put your business logic here
            Process_Status processStatus = new Process_Status();
            // Get a process variable
			/*String custId = (String) externalTask.getVariable("custId");
	            String msisdn = (String) externalTask.getVariable("msisdn");
	            String protype = (String) externalTask.getVariable("protype");
			 */	String instId= externalTask.getProcessInstanceId();
            //LOGGER.info("Failed Msisdn Review custId : '" + custId + "' , msisdn : '" + msisdn + "' and protype : '"+ protype +"...");

            processStatus.setCmreqid(instId);
            processStatus.setCmstatus("Process completed");

            //Insert values into msisdn table
            DbOperations.updateRecord(processStatus);
            
        } catch(Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        // Complete the task
        externalTaskService.complete(externalTask);
    }
}
