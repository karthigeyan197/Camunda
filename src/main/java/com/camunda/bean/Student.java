package com.camunda.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "student", uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length=11, nullable=false, unique=true)
	private Integer studentId;

	@Column(name = "name", length=20, nullable=true)
	private String studentName;

	@Column(name="age", length=5, nullable=true)
	private Integer studentAge;

	public Student() { }

	public Student(Integer studId, String studName, Integer studAge) {
		this.studentId = studId;
		this.studentName = studName;
		this.studentAge = studAge;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Integer getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}

	@Override
	public String toString() {
		return "Student= Id: " + this.studentId + ", Name: " + this.studentName + ", Age: " + this.studentAge;
	}
}