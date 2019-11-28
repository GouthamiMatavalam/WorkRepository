package studentapplication;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import io.student.details.producing_student_web_service.Student;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class StudentRepository {
	private static final Map<String, Student> students = new HashMap<>();

	@PostConstruct
	public void initData() {
		Student anil = new Student();
		anil.setName("Anil");
		anil.setCgpa(9);
		anil.setClazz(5);
		anil.setGrade("A+");

		students.put(anil.getName(), anil);

		Student sunil = new Student();
		sunil.setCgpa(5);
		sunil.setClazz(10);
		sunil.setGrade("C+");
		sunil.setName("Sunil");

		students.put(sunil.getName(), sunil);

		Student raju = new Student();
		raju.setCgpa(7);
		raju.setClazz(6);
		raju.setGrade("B+");
		raju.setName("Raju");

		students.put(raju.getName(), raju);
	}

	public Student findStudent(String name) {
		Assert.notNull(name, "The student's name must not be null");
		return students.get(name);
	}
}