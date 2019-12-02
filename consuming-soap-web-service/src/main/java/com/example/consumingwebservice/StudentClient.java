package com.example.consumingwebservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.consumingwebservice.wsdl.GetStudentRequest;
import com.example.consumingwebservice.wsdl.GetStudentResponse;

public class StudentClient extends WebServiceGatewaySupport {

  private static final Logger log = LoggerFactory.getLogger(StudentClient.class);

  public GetStudentResponse getStudent(String name) {

	  GetStudentRequest request = new GetStudentRequest();
    request.setName(name);

    log.info("Requesting location for " + name);

    GetStudentResponse response = (GetStudentResponse) getWebServiceTemplate()
        .marshalSendAndReceive("http://localhost:8080/ws/students", request,
            new SoapActionCallback(
                "http://student.io/details/gs-producing-web-service/GetCountryRequest"));

    return response;
  }

}