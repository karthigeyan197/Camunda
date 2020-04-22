package com.camunda.common;

import com.camunda.workers.*;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

public class WorkerDispatcher {

    @Value( "${bpmRestEndpoint}" )
    private static String bpmRestEndpoint = "http://localhost:8080/engine-rest";

    private final static Logger LOGGER = Logger.getLogger(WorkerDispatcher.class.getName());

    public static void initializeWorkers() {
        startWorker("process-loan", new LoanApproverProcessLoanWorker());
        startWorker("validate-claim", new ClaimProcessValidateClaimWorker());
        startWorker("process-claim", new ClaimProcessProcessClaimWorker());
        startWorker("process-postpaid", new AssignPostpaidMsIsdnWorker());
        startWorker("process-prepaid", new AssignPrepaidMsIsdnWorker());
        LOGGER.info("Workers are ready.");
    }

    private static void startWorker(String entityName, WorkerInterface workerInterface) {
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl(bpmRestEndpoint)
                //  .asyncResponseTimeout(10000) long polling timeout
                .build();

        // subscribe to an external task topic as specified in the process
        client.subscribe(entityName)
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler(workerInterface::executeBusinessLogic)
                .open();
    }
}
