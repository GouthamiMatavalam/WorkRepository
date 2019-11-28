package studentapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.student.details.producing_student_web_service.GetStudentRequest;
import io.student.details.producing_student_web_service.GetStudentResponse;

@Endpoint
public class StudentEndpoint {
	private static final String NAMESPACE_URI = "http://student.io/details/producing-student-web-service";

	private StudentRepository studentRepository;

	@Autowired
	public StudentEndpoint(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStudentRequest")
	@ResponsePayload
	public GetStudentResponse getStudent(@RequestPayload GetStudentRequest request) {
		GetStudentResponse response = new GetStudentResponse();
		response.setStudent(studentRepository.findStudent(request.getName()));

		return response;
	}
}