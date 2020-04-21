package com.camunda.workers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

import com.camunda.bean.MSISDN;
import com.camunda.util.DbOperations;

public class AssignPostpaidMsIsdnWorker implements WorkerInterface {

    private final static Logger LOGGER = Logger.getLogger(AssignPostpaidMsIsdnWorker.class.getName());

    public void executeBusinessLogic(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        LOGGER.info("Assign Postpaid MSISDN started");
        try {
            // Put your business logic here
            MSISDN dbobj = new MSISDN();
            // Get a process variable
            String custId = (String) externalTask.getVariable("custId");
            String msisdn = (String) externalTask.getVariable("msisdn");
            String protype = (String) externalTask.getVariable("protype");

            LOGGER.info("Assign Postpaid MSISDN. custId : '" + custId + "' , msisdn : '" + msisdn + "' and protype : '"+ protype +"...");

            dbobj.setCustId(custId);
            dbobj.setMsisdn(msisdn);
            dbobj.setProtype(protype);
            dbobj.setStatus("ACTIVE");
            
            //Insert values into msisdn table
            DbOperations.createRecord(dbobj);

        } catch(Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        // Complete the task
        externalTaskService.complete(externalTask);
    }

}
