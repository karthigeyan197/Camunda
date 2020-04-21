package com.camunda.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.camunda.bean.Student;

import org.apache.log4j.Logger;

public class DbOperations {

	public final static Logger logger = Logger.getLogger(DbOperations.class);

	// Method Used To Create The Hibernate's SessionFactory Object
	public static SessionFactory getSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		// Since Hibernate Version 4.x, Service Registry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 

		// Creating Hibernate Session Factory Instance
		SessionFactory factoryObj = configObj.buildSessionFactory(serviceRegistryObj);		
		return factoryObj;
	}

	// Method 1: This Method Used To Create A New Student Record In The Database Table
	public static Integer createRecord(Object studentObj) {
		Session sessionObj = getSessionFactory().openSession();

		//Creating Transaction Object  
		Transaction transObj = sessionObj.beginTransaction();
		sessionObj.save(studentObj);

		// Transaction Is Committed To Database
		transObj.commit();

		// Closing The Session Object
		sessionObj.close();
		logger.info("Successfully Created " + studentObj.toString());
		return null;
	}

	// Method 2: This Method Is Used To Display The Records From The Database Table
	@SuppressWarnings("unchecked")
	public static List<Student> displayRecords() {
		Session sessionObj = getSessionFactory().openSession();
		List<Student> studentsList = sessionObj.createQuery("FROM Student").list();

		// Closing The Session Object
		sessionObj.close();
		logger.info("Student Records Available In Database Are?= " + studentsList.size());
		return studentsList;
	}

	// Method 3: This Method Is Used To Update A Record In The Database Table
	public static void updateRecord(Student studentObj) {
		Session sessionObj = getSessionFactory().openSession();

		//Creating Transaction Object  
		Transaction transObj = sessionObj.beginTransaction();
		Student stuObj = (Student) sessionObj.load(Student.class, studentObj.getStudentId());
		stuObj.setStudentName(studentObj.getStudentName());
		stuObj.setStudentAge(studentObj.getStudentAge());

		// Transaction Is Committed To Database
		transObj.commit();

		// Closing The Session Object
		sessionObj.close();
		logger.info("Student Record Is Successfully Updated!= " + studentObj.toString());
	}

	// Method 4(a): This Method Is Used To Delete A Particular Record From The Database Table
	public static void deleteRecord(Integer studentId) {
		Session sessionObj = getSessionFactory().openSession();

		//Creating Transaction Object  
		Transaction transObj = sessionObj.beginTransaction();
		Student studObj = findRecordById(studentId);
		sessionObj.delete(studObj);

		// Transaction Is Committed To Database
		transObj.commit();

		// Closing The Session Object
		sessionObj.close();
		logger.info("Successfully Record Is Successfully Deleted!=  " + studObj.toString());

	}

	// Method 4(b): This Method To Find Particular Record In The Database Table
	public static Student findRecordById(Integer studentId) {
		Session sessionObj = getSessionFactory().openSession();
		Student stu = (Student) sessionObj.load(Student.class, studentId);

		// Closing The Session Object
		sessionObj.close();
		return stu;
	}

	// Method 5: This Method Is Used To Delete All Records From The Database Table
	public static void deleteAllRecords() {
		Session sessionObj = getSessionFactory().openSession();

		//Creating Transaction Object  
		Transaction transObj = sessionObj.beginTransaction();
		Query queryObj = sessionObj.createQuery("DELETE FROM Student");
		queryObj.executeUpdate();

		// Transaction Is Committed To Database
		transObj.commit();

		// Closing The Session Object
		sessionObj.close();
		logger.info("Successfully Deleted All Records From The Database Table!");
	}
}