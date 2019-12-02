package org.service;

import java.util.List;

import javax.transaction.Transactional;
import org.dao.StudentDao;
import org.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service("studentService")
public class StudentService {
 
	@Autowired
	StudentDao studentDao;
 
	@Transactional
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}
 
	@Transactional
	public Student getStudent(int id) {
		return studentDao.getStudent(id);
	}
 
	@Transactional
	public Student addStudent(Student student) {
		studentDao.addStudent(student);
		return student;
	}
 
	@Transactional
	public Student updateStudent(Student student) {
		studentDao.updateStudent(student);
		return student;
	}
 
	@Transactional
	public void deleteStudent(int id) {
		studentDao.deleteStudent(id);
	}
}
