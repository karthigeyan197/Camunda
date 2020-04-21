package com.camunda.test.WorkFlowRestWorker;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.camunda.common.WorkerDispatcher;

@SpringBootApplication
@EnableProcessApplication("WorkFlowRestWorker")
public class CamundaApplication {
  public static void main(String... args) {
    SpringApplication.run(CamundaApplication.class, args);
    WorkerDispatcher.initializeWorkers();
  }
}
