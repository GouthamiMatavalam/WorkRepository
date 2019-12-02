package org.controller;

import java.util.List;

import org.model.Student;
import org.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {
 
	@Autowired
	StudentService studentService;
 
	@RequestMapping(value = "/getAllStudents", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Student> getAllStudents(Model model) {
 
		List<Student> listOfStudents = studentService.getAllStudents();
		model.addAttribute("student", new Student());
		model.addAttribute("listOfStudents", listOfStudents);
		return listOfStudents;
	}
 
	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String goToHomePage() {
		return "redirect:/getAllStudents";
	}
 
	@RequestMapping(value = "/getStudent/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public void getStudentById(@PathVariable int id) {
		studentService.getStudent(id);
	}
 
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST, headers = "Accept=application/json")
	public Student addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
 
	}
 
	@RequestMapping(value = "/addStudent", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Student updateStudent(@RequestBody Student student) {
		return studentService.updateStudent(student); 
 
	}	
 
	@RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteStudent(@PathVariable("id") int id) {
		studentService.deleteStudent(id);
 
 
	}	
}
 