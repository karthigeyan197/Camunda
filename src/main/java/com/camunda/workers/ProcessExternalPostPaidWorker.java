package com.camunda.workers;

import org.apache.commons.lang3.RandomUtils;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessExternalPostPaidWorker implements WorkerInterface {

    private final static Logger LOGGER = Logger.getLogger(ProcessExternalPostPaidWorker.class.getName());

    public void executeBusinessLogic(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        LOGGER.info("Process external postpaid MSISDN Review");
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            Boolean isFirstTime = (Boolean) externalTask.getVariable("wait");
            String custId = (String) externalTask.getVariable("custId");
            // Mocking call for Postpaid Review with external application

            // Some asynchronous calls

            if(isFirstTime != null) {
                if(custId.contains("N")){
                    map.put("wait", false);
                    map.put("approved", false);
                    externalTaskService.complete(externalTask, map);
                } else {
                    map.put("wait", false);
                    map.put("approved", true);
                    externalTaskService.wait();
                }
            }

            LOGGER.info("External postpaid MSISDN Review Completed");
        } catch(Exception e){
            map.put("wait", false);
            map.put("approved", false);
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            externalTaskService.complete(externalTask, map);
        }

    }

}
