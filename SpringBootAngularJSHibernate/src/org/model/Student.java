package org.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
/*
 * This is our model class and it corresponds to Student table in database
 */
@Entity
@Table(name="STUDENT")
public class Student{
 
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
 
	@Column(name="studentName")
	String studentName; 
 
	@Column(name="email")
	String email;
 
	public Student() {
		super();
	}
	public Student(String studentName,String email) {
		super();
		this.studentName=studentName;
		this.email=email;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}