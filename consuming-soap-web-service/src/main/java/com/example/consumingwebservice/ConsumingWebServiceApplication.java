package com.example.consumingwebservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.consumingwebservice.wsdl.GetStudentResponse;

@SpringBootApplication
public class ConsumingWebServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConsumingWebServiceApplication.class, args);
  }

  @Bean
  CommandLineRunner lookup(StudentClient quoteClient) {
    return args -> {
      String name = "Anil";

      if (args.length > 0) {
    	  name = args[0];
      }
      GetStudentResponse response = quoteClient.getStudent(name);
      System.err.println(response.getStudent().getGrade());
    };
  }

}