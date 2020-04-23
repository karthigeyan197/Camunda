package com.camunda.workers;

import org.apache.commons.lang3.RandomUtils;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessExternalPostPaidWorker implements WorkerInterface {

    private final static Logger LOGGER = Logger.getLogger(ProcessExternalPostPaidWorker.class.getName());

    public void executeBusinessLogic(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        LOGGER.info("Process external postpaid MSISDN Review");
        try {
            // Mocking call for Postpaid Review with external application
            // Some business call
            Thread.sleep(10000);
            if(RandomUtils.nextBoolean()){
                externalTaskService.complete(externalTask, Collections.singletonMap("approved", true));
            } else {
                externalTaskService.complete(externalTask, Collections.singletonMap("approved", false));
            }
            LOGGER.info("External postpaid MSISDN Review Completed");
        } catch(Exception e){
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            externalTaskService.complete(externalTask, Collections.singletonMap("approved", false));
        }

    }

}
