package org.dao;

import java.util.List;
import org.dao.StudentDao;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
@Repository
public class StudentDaoImpl implements StudentDao{
 
	@Autowired
	private SessionFactory sessionFactory;
 
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
 
	public List<Student> getAllStudents() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Student>  studentList = session.createQuery("from Student").list();
		return studentList;
	}
 
	public Student getStudent(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Student student = (Student) session.get(Student.class, id);
		return student;
	}
 
	public Student addStudent(Student student) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(student);
		return student;
	}
 
	public void updateStudent(Student student) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(student);
	}
 
	public void deleteStudent(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Student p = (Student) session.load(Student.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	} 
}
