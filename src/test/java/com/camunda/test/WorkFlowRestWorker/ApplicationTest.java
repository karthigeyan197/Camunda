package com.camunda.test.WorkFlowRestWorker;

import org.apache.log4j.Logger;

import com.camunda.bean.MSISDN;
import com.camunda.util.DbOperations;

public class ApplicationTest {

	public final static Logger logger = Logger.getLogger(ApplicationTest.class);

	public static void main(String[] args) {
		
		MSISDN msisdn = new MSISDN("9765324223","6542331346","P001","ACTIVE");
		
		DbOperations.createRecord(msisdn);
		
		
		

		/*	Student studentObj1 = new Student(4, "hello", 20);
		Student studentObj2 = new Student(5, "Harry ", 10);
		Student studentObj3 = new Student(6, "welcome", 5);

		logger.info("\n=======CREATE RECORDS=======");
		
		
		DbOperations.createRecord(studentObj1);
		DbOperations.createRecord(studentObj2);
		DbOperations.createRecord(studentObj3);

		/*logger.info("\n=======READ RECORDS=======");
		List<Student>viewStudent = DbOperations.displayRecords();
		for(Student student : viewStudent) {
			logger.info(student.toString());
		}

		logger.info("\n=======UPDATE RECORDS=======");
		studentObj1.setStudentAge(25);
		studentObj1.setStudentName("Java Code Geek");
		DbOperations.updateRecord(studentObj1);
		logger.info("\n=======READ RECORDS AFTER UPDATION=======");
		List<Student> updateStudent = DbOperations.displayRecords();
		for(Student student : updateStudent) {
			logger.info(student.toString());
		}

		logger.info("\n=======DELETE RECORD=======");
		DbOperations.deleteRecord(studentObj2.getStudentId());
		logger.info("\n=======READ RECORDS AFTER DELETION=======");
		List<Student> deleteStudentRecord = DbOperations.displayRecords();
		for(Student student : deleteStudentRecord) {
			logger.info(student.toString());
		}

		logger.info("\n=======DELETE ALL RECORDS=======");
		DbOperations.deleteAllRecords();
		logger.info("\n=======READ RECORDS AFTER ALL RECORDS DELETION=======");
		List<Student> deleteAll = DbOperations.displayRecords();
		for(Student student : deleteAll) {
			logger.info(student.toString());
		}
		System.exit(0);
*/	}
}